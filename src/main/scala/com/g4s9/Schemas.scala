package com.g4s9

import org.apache.spark.sql.types.{FloatType, IntegerType, LongType, StringType, StructField, StructType}

object Schemas {
  val pageViewSchema: StructType = StructType(Array(
    StructField("ts", LongType),
    StructField("sessionId", LongType),
    StructField("page", StringType),
    StructField("auth", StringType),
    StructField("method", StringType),
    StructField("status", IntegerType),
    StructField("level", StringType),
    StructField("itemSession", IntegerType),
    StructField("city", StringType),
    StructField("zip", StringType),
    StructField("state", StringType),
    StructField("userAgent", StringType),
    StructField("lon", FloatType),
    StructField("lat", FloatType),
    StructField("userId", IntegerType),
    StructField("lastName", StringType),
    StructField("firstName", StringType),
    StructField("gender", StringType),
    StructField("registration", LongType),
    StructField("artist", StringType),
    StructField("song", StringType),
    StructField("duration", FloatType),
  ))
}
