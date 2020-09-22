package com.cyclone.staffapp

import com.cyclone.staffapp.network.Person
import com.cyclone.staffapp.network.Specialty

class Storage {

    companion object {
        lateinit var specialty: List<Specialty>
        lateinit var persons: List<Person>
    }
}