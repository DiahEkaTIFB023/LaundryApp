package com.example.laundryapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.laundryapp.model.laundrymodel
import kotlinx.coroutines.flow.Flow


@Dao
interface daolaundry {
    @Query("SELECT * FROM laundry_table ORDER BY name ASC")
    fun getLaundryModel(): Flow<List<laundrymodel>>

    //untuk menambah data
    @Insert
    suspend fun insert(laundryModel: laundrymodel)
    //untuk menghapus data
    @Delete
    suspend fun delete(laundryModel: laundrymodel)
    //untuk mengupdate data
    @Update
    suspend fun update(laundryModel: laundrymodel)
}