package com.bangkit.caraka.data

import com.bangkit.caraka.R

object AksaraBaliData {

    fun getQuestionQuestionBali(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val soal1 = Question(
            1,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_ga_min,
            "ha",
            "na",
            "ga",
            "ra",
            3
        )

        val soal2 = Question(
            2,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_ka_min,
            "ya",
            "ka",
            "nga",
            "ba",
            2
        )

        val soal3 = Question(
            3,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_ya_min,
            "nga",
            "ya",
            "ka",
            "pa",
            2
        )

        val soal4 = Question(
            4,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_ra_min,
            "ya",
            "ba",
            "nga",
            "ra",
            4
        )

        val soal5 = Question(
            5,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_ja_min,
            "ja",
            "ha",
            "na",
            "ma",
            1
        )

        val soal6 = Question(
            6,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_ma_min,
            "ba",
            "ra",
            "nga",
            "ma",
            4
        )

        val soal7 = Question(
            7,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_ba_min,
            "na",
            "ba",
            "nya",
            "nga",
            2
        )

        val soal8 = Question(
            8,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_nya_min,
            "ma",
            "wa",
            "ba",
            "nya",
            4
        )

        val soal9 = Question(
            9,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_wa_min,
            "na",
            "ha",
            "wa",
            "ca",
            3
        )

        val soal10 = Question(
            10,
            "Apa Artinya Aksara Ini?",
            R.drawable.bali_sa_min,
            "sa",
            "nga",
            "ta",
            "nya",
            1
        )

        questionsList.add(soal1)
        questionsList.add(soal2)
        questionsList.add(soal3)
        questionsList.add(soal4)
        questionsList.add(soal5)
        questionsList.add(soal6)
        questionsList.add(soal7)
        questionsList.add(soal8)
        questionsList.add(soal9)
        questionsList.add(soal10)

        return questionsList
    }

    fun getAksaraKamus(): List<Kamus> {
        return listOf(
            Kamus(1, 1, R.drawable.bali_ha_min, "Ha"),
            Kamus(2, 1, R.drawable.bali_na_min, "Na"),
            Kamus(3, 1, R.drawable.bali_ca_min, "Ca"),
            Kamus(4, 1, R.drawable.bali_ra_min, "Ra"),
            Kamus(5, 1, R.drawable.bali_ka_min, "Ka"),

            Kamus(6, 1, R.drawable.bali_da_min, "Da"),
            Kamus(7, 1, R.drawable.bali_ta_min, "Ta"),
            Kamus(8, 1, R.drawable.bali_sa_min, "Sa"),
            Kamus(9, 1, R.drawable.bali_wa_min, "Wa"),
            Kamus(10, 1, R.drawable.bali_la_min, "La"),

            Kamus(11, 1, R.drawable.bali_ma_min, "Ma"),
            Kamus(12, 1, R.drawable.bali_ga_min, "Ga"),
            Kamus(13, 1, R.drawable.bali_ba_min, "Ba"),
            Kamus(14, 1, R.drawable.bali_nga_min, "Nga"),
            Kamus(15, 1, R.drawable.bali_pa_min, "Pa"),

            Kamus(16, 1, R.drawable.bali_ja_min, "Ja"),
            Kamus(17, 1, R.drawable.bali_ya_min, "Ya"),
            Kamus(18, 1, R.drawable.bali_nya_min, "Nya"),
        )
    }

}