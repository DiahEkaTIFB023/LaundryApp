package com.example.laundryapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.laundryapp.model.LaundryModell
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoLaundry {
    @Query("SELECT * FROM laundry_table ORDER BY name ASC")
    fun getLaundryModell(): Flow<List<LaundryModell>>

    //untuk menambah data
    @Insert
    suspend fun insert(LaundryModell: LaundryModell)
    //untuk menghapus data
    @Delete
    suspend fun delete(LaundryModell: LaundryModell)
    //untuk mengupdate data
    @Update
    suspend fun update(LaundryModell: LaundryModell)
}