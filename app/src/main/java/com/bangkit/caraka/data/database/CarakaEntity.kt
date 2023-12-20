package com.bangkit.caraka.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Question(
    @PrimaryKey
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int,
)


@Entity
data class Kamus(
    @PrimaryKey
    val id: Int,
    val kamusId: Int,
    val aksara: Int,
    val latin: String,
)

@Entity
data class Artikel(
    @PrimaryKey
    val id: Int,
    val artikelId: Int,
    val judul: String,
    val img_sejarah: String,
    val desc : String,
)

//@Entity
//data class Langganan(
//    @PrimaryKey
//    val id: Int,
//    val langgananId: Int,
//    val durasi: Int,
//    val harga: Int,
//)