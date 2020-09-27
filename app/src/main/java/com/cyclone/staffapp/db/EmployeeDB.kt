package com.cyclone.staffapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI
import java.util.*

@Entity
data class EmployeeDB(
    val firstName: String,
    val lastName: String,
    val birthday: Date?,
    val avatarURI: URI?,
    val specialty: List<Long>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}