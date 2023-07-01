package com.example.laundryapp.model

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "laundry_table")
@Parcelize
data class LaundryModell(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val address: String,
    val paket: String,
    val weight: String,
    val price: String,
) : Parcelable
