package com.bangkit.caraka.data

data class Question(
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int,
)

data class Kamus(
    val id: Int,
    val kamusBelajarId: Int,
    val aksara: Int,
    val latin: String,
)