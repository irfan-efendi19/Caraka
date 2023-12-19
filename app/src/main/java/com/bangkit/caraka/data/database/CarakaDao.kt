package com.bangkit.caraka.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarakaDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertQuestion(quiz: List<Question>)
//
//    @Query("SELECT * from kamus WHERE kamusId = :questionId")
//    fun getAllQuestion(questionId: Int): LiveData<List<Question>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArtikel(artikel: List<Artikel>)

    @Query("SELECT * from artikel WHERE artikelId = :artikelId")
    fun getAllArtikel(artikelId: Int): LiveData<List<Artikel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertKamus(kamus: List<Kamus>)

    @Query("SELECT * from kamus WHERE kamusId = :aksaraId")
    fun getAllKamus(aksaraId: Int): LiveData<List<Kamus>>
}