package com.bangkit.caraka.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Question::class, Kamus::class, Artikel::class],
    version = 2,
    exportSchema = false
)
abstract class CarakaDatabase: RoomDatabase() {

    abstract fun CarakaDao(): CarakaDao

    companion object {
        @Volatile
        private var INSTANCE: CarakaDatabase? = null

        fun getDatabase(context: Context): CarakaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarakaDatabase::class.java,
                    "caraka_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }

        fun destroyDatabase(context: Context){
            val dbFile = context.getDatabasePath("baksara_db")
            dbFile.delete()
            INSTANCE = null
        }
    }
}