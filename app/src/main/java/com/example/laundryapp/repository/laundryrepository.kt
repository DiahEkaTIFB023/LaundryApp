package com.example.laundryapp.repository

import com.example.laundryapp.dao.daolaundry
import com.example.laundryapp.model.laundrymodel
import kotlinx.coroutines.flow.Flow

class laundryrepository(private val DaoLaundry: daolaundry) {
    val allLaundry: Flow<List<laundrymodel>> = DaoLaundry.getLaundryModel()

    suspend fun insert(LaundryModel: laundrymodel) {
        DaoLaundry.insert(LaundryModel)
    }

    suspend fun delete(LaundryModel: laundrymodel) {
        DaoLaundry.delete(LaundryModel)
    }

    suspend fun update(LaundryModel: laundrymodel) {
        DaoLaundry.update(LaundryModel)
    }
}
