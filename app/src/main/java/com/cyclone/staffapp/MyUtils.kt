package com.cyclone.staffapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.setImage(context: Context?, uri: Uri?) {
    Glide.with(context!!)
        .load(uri)
        .circleCrop()
        .placeholder(R.drawable.ic_cap)
        .into(this)
}

@SuppressLint("ConstantLocale")
private val patterns = arrayListOf(
    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()),
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
)

fun getDate(birthday: String?): Date? {
    if (!birthday.isNullOrEmpty())
        for (pattern in patterns) {
            val date = Date(pattern.parse(birthday)!!.time)
            if (date.time > 0)
                return date
        }
    return null
}

fun Date?.getAge(): String {
    if (this == null) return "Неизвестно"

    val today = Calendar.getInstance()
    val birthday = Calendar.getInstance()
    birthday.time = this

    return (today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR)).toString()
}

fun getYearString(year: String): String {
    return when (year.last()) {
        '1' -> "год"
        in '2'..'4' -> "года"
        in '5'..'9','0' -> "лет"
        else -> ""
    }
}