package com.example.laundryapp.application


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.laundryapp.dao.daolaundry
import com.example.laundryapp.model.laundrymodel

@Database(entities = [laundrymodel::class], version = 2, exportSchema = false)
abstract class laundrydatabase: RoomDatabase() {
    abstract fun DaoLaundry(): daolaundry

    companion object {
        private var INSTANCE: laundrydatabase? = null

//        private val migration1To2: Migration = object: Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE tire_table ADD COLUMN latitude Double DEFAULT 0.0")
//                database.execSQL("ALTER TABLE tire_table ADD COLUMN longitude Double DEFAULT 0.0")
//            }
//        }

        fun getDatabase(
            context: Context
        ): laundrydatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    laundrydatabase::class.java,
                    "laundry_database"
                )
//                    .addMigrations(migration1To2)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}