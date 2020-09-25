package com.cyclone.staffapp.workers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cyclone.staffapp.R
import com.cyclone.staffapp.Storage
import kotlinx.android.synthetic.main.workers_fragment.*

class WorkersFragment : Fragment(R.layout.workers_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val id = arguments!!.getInt("id")
            workersRecycler.adapter = WorkersAdapter(Storage.persons.filter { person -> person.specialty.map { specialty -> specialty.id }.contains(id) })
        }
    }
}