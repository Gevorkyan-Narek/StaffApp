package com.cyclone.staffapp.model

import android.util.Log
import com.cyclone.staffapp.getDate
import com.squareup.moshi.*
import java.util.*

data class Person(
    @Json(name = "f_name")
    val firstName: String,
    @Json(name = "l_name")
    val lastName: String,
    @Json(name = "birthday")
    var birthday: Date?,
    @Json(name = "avatr_url")
    val avatarUrl: String?,
    @Json(name = "specialty")
    val specialty: List<Specialty>
)

class DateJsonAdapter : JsonAdapter<Date?>() {

    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        reader.apply {
            return when (peek()) {
                JsonReader.Token.STRING -> {
                    val birthday = nextString()
//                    if (birthday.isNullOrEmpty()) {
//                        null
//                    } else {
                        getDate(birthday)
//                    }
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
    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }
}