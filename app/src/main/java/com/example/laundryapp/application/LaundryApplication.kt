package com.example.laundryapp.application

import android.app.Application
import com.example.laundryapp.repository.LaundryRepository


class LaundryApplication: Application() {
    val database by lazy { LaundryDatabases.getDatabase(this) }
    val repository by lazy { LaundryRepository(database.DaoLaundry()) }
}
