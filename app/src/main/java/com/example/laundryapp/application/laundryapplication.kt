package com.example.laundryapp.application

import android.app.Application
import com.example.laundryapp.repository.laundryrepository


class laundryapplication: Application() {
    val database by lazy { laundrydatabase.getDatabase(this) }
    val repository by lazy { laundryrepository(database.DaoLaundry()) }
}
