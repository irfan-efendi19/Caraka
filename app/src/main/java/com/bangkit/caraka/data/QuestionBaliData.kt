package com.bangkit.caraka.data

import com.bangkit.caraka.R

object QuestionBaliData {

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

}