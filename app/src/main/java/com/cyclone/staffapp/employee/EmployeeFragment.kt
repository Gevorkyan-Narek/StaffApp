package com.cyclone.staffapp.employee

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cyclone.staffapp.R
import com.cyclone.staffapp.Storage
import kotlinx.android.synthetic.main.employee_fragment.*

class EmployeeFragment : Fragment(R.layout.employee_fragment) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getInt("id")

        val person = Storage.persons[id]
        name.text = "${person.firstName} ${person.lastName}"
        birthday.text = person.birthday ?: "-"
        specialty.text = person.specialty.joinToString(", ") { specialty -> specialty.name }
        avatar.setImage(person.avatarUrl)
    }

    private fun ImageView.setImage(url: String) {
        Glide.with(context)
            .load(url)
            .transform(RoundedCorners(15))
            .into(this)
    }
}