package com.cyclone.staffapp

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.setImage(url: String?) {
    Glide.with(context)
        .load(url)
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
            Log.d("FromJson: Date", date.time.toString())
            if (date.time > 0)
                return date
        }
    return null
}