package com.cyclone.staffapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpecialtyDB (
    @PrimaryKey
    val id: Long,
    val name: String
)