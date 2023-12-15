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