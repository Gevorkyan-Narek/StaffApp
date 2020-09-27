package com.cyclone.staffapp.json_adapters

import com.squareup.moshi.FromJson
import java.util.*

class StringJsonAdapter {

    @FromJson
    fun fromJson(text: String): String {
        val lower = text.toLowerCase(Locale.ROOT)
        return lower.substring(0,1).toUpperCase(Locale.ROOT) + lower.substring(1)
    }
}