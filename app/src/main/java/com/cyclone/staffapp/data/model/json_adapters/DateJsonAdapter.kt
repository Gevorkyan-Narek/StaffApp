package com.cyclone.staffapp.data.model.json_adapters

import com.cyclone.staffapp.getDate
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.util.*

class DateJsonAdapter {

    @FromJson
    fun fromJson(reader: JsonReader): Date? {
        reader.apply {
            return when (peek()) {
                JsonReader.Token.STRING -> {
                    val birthday = nextString()
                    getDate(birthday)
                }
                JsonReader.Token.NULL -> {
                    nextNull()
                }
                else -> {
                    null
                }
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }
}