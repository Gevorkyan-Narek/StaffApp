package com.cyclone.staffapp.domain.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class EmployeeDB(
  val firstName: String,
  val lastName: String,
  val birthday: Date?,
  val avatarURI: Uri?,
  val specialty: List<Long>
) {
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0L
}