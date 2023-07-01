package com.example.laundryapp.application


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.laundryapp.dao.DaoLaundry
import com.example.laundryapp.model.LaundryModell

@Database(entities = [LaundryModell::class], version = 2, exportSchema = false)
abstract class LaundryDatabases: RoomDatabase() {
    abstract fun DaoLaundry(): DaoLaundry

    companion object {
        private var INSTANCE: LaundryDatabases? = null

//        private val migration1To2: Migration = object: Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE tire_table ADD COLUMN latitude Double DEFAULT 0.0")
//                database.execSQL("ALTER TABLE tire_table ADD COLUMN longitude Double DEFAULT 0.0")
//            }
//        }

        fun getDatabase(
            context: Context
        ): LaundryDatabases {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LaundryDatabases::class.java,
                    "laundry_database_1"
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
