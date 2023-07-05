package com.example.laundryapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "laundry_table")
@Parcelize
data class LaundryModell(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val no: String,
    val email: String,
    val branch: String,
    val address: String,
    val latitude: Double?,
    val longitude: Double?

) : Parcelable
