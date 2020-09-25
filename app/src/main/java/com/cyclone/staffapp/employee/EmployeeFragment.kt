package com.cyclone.staffapp.employee

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.cyclone.staffapp.R
import com.cyclone.staffapp.Storage
import com.cyclone.staffapp.setImage
import kotlinx.android.synthetic.main.employee_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class EmployeeFragment : Fragment(R.layout.employee_fragment) {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = arguments!!.getString("firstName")
        val lastName = arguments!!.getString("lastName")

        val person =
            Storage.persons.find { person -> person.firstName == firstName && person.lastName == lastName }!!
        name.text = "$firstName $lastName"
        Log.d("Employee", person.birthday.toString())
        birthday.text = if (person.birthday == null) "-"
        else SimpleDateFormat("dd.MM.YYYY", Locale.getDefault()).format(person.birthday!!)
        specialty.text = person.specialty.joinToString(", ") { specialty -> specialty.name }
        avatar.setImage(person.avatarUrl)
    }

}