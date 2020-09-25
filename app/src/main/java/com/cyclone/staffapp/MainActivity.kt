package com.cyclone.staffapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cyclone.staffapp.specialty.SpecialtyFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment, SpecialtyFragment()).commit()
    }
}