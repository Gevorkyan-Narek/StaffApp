package com.cyclone.staffapp.json_adapters

import com.cyclone.staffapp.getDate
import com.squareup.moshi.*
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