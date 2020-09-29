package com.cyclone.staffapp.data.db

import android.net.Uri
import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromString(value: String?): Uri? {
        return Uri.parse(value)
    }

    @TypeConverter
    fun UriToString(value: Uri?): String? {
        return value.toString()
    }

    @TypeConverter
    fun fromList(value: List<Long>): String? {
        return value.joinToString(",")
    }

    @TypeConverter
    fun StringToLong(value: String?): List<Long>? {
        return value?.split(",")?.map { s -> s.toLong() }
    }
}
