package com.cyclone.staffapp.presentation.ui

import android.os.Bundle
import android.view.View
import com.cyclone.staffapp.R
import com.cyclone.staffapp.domain.repositories.specialty.SpecialtyDataRepo
import com.cyclone.staffapp.presentation.adapter.SpecialtyAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.speciality_fragment.*

class SpecialtyFragment : MainView(R.layout.speciality_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        specialtyRecycler.adapter = SpecialtyAdapter(listOf())
        getData()
    }

    override fun getData() {
        SpecialtyDataRepo.getInstance()
            .getAll()
            .doOnNext { list ->
                specialtyRecycler.adapter = SpecialtyAdapter(list)
            }
            .subscribe()
    }
}