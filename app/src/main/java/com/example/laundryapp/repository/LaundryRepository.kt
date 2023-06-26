package com.example.laundryapp.repository

import com.example.laundryapp.dao.DaoLaundry
import com.example.laundryapp.model.LaundryModell
import kotlinx.coroutines.flow.Flow

class LaundryRepository(private val DaoLaundry: DaoLaundry) {
    val allLaundry: Flow<List<LaundryModell>> = DaoLaundry.getLaundryModell()

    suspend fun insert(LaundryModell: LaundryModell) {
        DaoLaundry.insert(LaundryModell)
    }

    suspend fun delete(LaundryModell: LaundryModell) {
        DaoLaundry.delete(LaundryModell)
    }

    suspend fun update(LaundryModell: LaundryModell) {
        DaoLaundry.update(LaundryModell)
    }
}
