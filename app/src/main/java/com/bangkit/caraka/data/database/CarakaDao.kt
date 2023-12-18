package com.bangkit.caraka.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.caraka.data.database.Kamus

@Dao
interface CarakaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKamus(kamus: List<Kamus>)

    @Query("SELECT * from kamus WHERE kamusId = :aksaraId")
    fun getAllKamus(aksaraId: Int): LiveData<List<Kamus>>
}