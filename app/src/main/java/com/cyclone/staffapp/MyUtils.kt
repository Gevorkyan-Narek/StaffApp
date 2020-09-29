package com.cyclone.staffapp

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.setImage(context: Context?, uri: Uri?) {
    Glide.with(context!!)
        .load(uri)
        .transform(RoundedCorners(15))
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