package com.cyclone.staffapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.staffapp.R
import com.cyclone.staffapp.data.db.DataBase
import com.cyclone.staffapp.domain.usecases.RetrofitUseCase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBase.initDataBase(this)

        RetrofitUseCase.loadData()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, SpecialtyFragment())
            .commit()
    }
}