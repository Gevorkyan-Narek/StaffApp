package com.cyclone.staffapp

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.net.URI

class URLJsonAdapter{

    @FromJson
    fun fromJson(reader: JsonReader): URI? {
        reader.apply {
            return when (peek()) {
                JsonReader.Token.NULL -> {
                    nextNull()
                }
                JsonReader.Token.STRING -> {
                    val string = nextString()
                    if (string.isNotEmpty()) URI(string)
                    else null
                }
                else -> {
                    null
                }
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: URI?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }
}