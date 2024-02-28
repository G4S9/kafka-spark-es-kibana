spark-submit \
  --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.0,org.elasticsearch:elasticsearch-spark-30_2.12:8.12.2 \
  /app/target/scala-2.12/kafka-spark-es-kibana_2.12-1.0.0-SNAPSHOT.jar
