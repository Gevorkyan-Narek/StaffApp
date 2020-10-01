package com.cyclone.staffapp.presentation.ui

import android.os.Bundle
import android.view.View
import com.cyclone.staffapp.R
import com.cyclone.staffapp.domain.usecases.SpecialtyUseCase
import com.cyclone.staffapp.presentation.adapter.SpecialtyAdapter
import kotlinx.android.synthetic.main.speciality_fragment.*

class SpecialtyFragment : MainView(R.layout.speciality_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    override fun getData() {
        SpecialtyUseCase.getInstance()
            .getAll()
            .doOnNext { list ->
                specialtyRecycler.adapter = SpecialtyAdapter(list)
            }
            .subscribe()
    }
}