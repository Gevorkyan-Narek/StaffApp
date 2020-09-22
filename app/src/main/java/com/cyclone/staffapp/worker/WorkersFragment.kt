package com.cyclone.staffapp.worker

import android.os.Bundle
import android.util.Log
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
            Log.d("Workers", id.toString())
            workersRecycler.adapter = WorkersAdapter(Storage.persons.filter { person -> person.specialty.map { specialty -> specialty.id }.contains(id) })
        }
    }
}