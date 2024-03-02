package com.g4s9

import com.g4s9.Schemas.pageViewSchema
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object ElasticsearchLoader {
  def main(args: Array[String]): Unit = {
    val SPARK_MASTER_URL = sys.env.getOrElse(
      "SPARK_MASTER_URL", throw new RuntimeException("SPARK_MASTER_URL not defined")
    )

    val KAFKA_BOOTSTRAP_SERVERS = sys.env.getOrElse(
      "KAFKA_BOOTSTRAP_SERVERS", throw new RuntimeException("KAFKA_BOOTSTRAP_SERVERS not defined")
    )

    val ES_NODES = sys.env.getOrElse(
      "ES_NODES", throw new RuntimeException("ES_NODES not defined")
    )

    val ES_PORT = sys.env.getOrElse(
      "ES_PORT", throw new RuntimeException("ES_PORT not defined")
    )

    val CHECKPOINT_LOCATION = sys.env.getOrElse(
      "CHECKPOINT_LOCATION", throw new RuntimeException("CHECKPOINT_LOCATION not defined")
    )

    val spark = SparkSession.builder()
      .master(SPARK_MASTER_URL)
      .config("es.nodes", ES_NODES)
      .config("es.port", ES_PORT)
      .appName("ElasticsearchLoader")
      .getOrCreate()

    import spark.implicits._

    spark.sparkContext.setLogLevel("WARN")

    spark.conf.set("spark.sql.session.timeZone", "UTC")

    spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", KAFKA_BOOTSTRAP_SERVERS)
      .option("subscribe", "page_view_events")
      .load()

      .select(from_json(expr("cast(value as string)"), pageViewSchema).as("json"))
      .select("json.*")
      .withColumn("@timestamp", from_unixtime($"ts" / 1000, "yyyy-MM-dd'T'HH:mm:ss'Z'"))

      .writeStream
      .format("es")
      .option("checkpointlocation", CHECKPOINT_LOCATION)
      .start("spark")

      .awaitTermination()
  }
}
