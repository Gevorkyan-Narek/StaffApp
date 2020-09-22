package com.cyclone.staffapp

import com.cyclone.staffapp.model.Person
import com.cyclone.staffapp.model.Specialty

class Storage {

    companion object {
        lateinit var specialty: List<Specialty>
        lateinit var persons: List<Person>
    }
}