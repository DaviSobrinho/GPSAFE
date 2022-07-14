package com.example.gpsafe_for_galaxym52.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gpsafe_for_galaxym52.database.dao.ClienteDao
import com.example.gpsafe_for_galaxym52.model.clientes


@Database(entities = [clientes::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao

    companion object {
        fun instancia(context : Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "gpsafe_for_galaxym52.db"
            ).allowMainThreadQueries()
                .build()
        }
    }

}