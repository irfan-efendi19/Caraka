package com.bangkit.caraka.data

import com.bangkit.caraka.R

object QuestionBaliData {

    fun getQuestionQuestionBali(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val soal1 = Question(
            1,
            "Apa Artinya Aksara Ini?",
            R.drawable.banner,
            "Australia",
            "Argentina",
            "Armenia",
            "USA",
            1
        )

        val soal2 = Question(
            2,
            "What country flag is this", R.drawable.banner,
            "Australia",
            "Argentina",
            "Mexico",
            "USA",
            3
        )

        val soal3 = Question(
            3,
            "What country flag is this", R.drawable.banner,
            "Mexico",
            "France",
            "Africa",
            "USA",
            2
        )

        val soal4 = Question(
            4,
            "What country flag is this", R.drawable.banner,
            "Kazakistan",
            "Ukraine",
            "Turkey",
            "USA",
            3
        )

        val soal5 = Question(
            5,
            "What country flag is this", R.drawable.banner,
            "USA",
            "Argentina",
            "Armenia",
            "South America",
            1
        )

        val soal6 = Question(
            6,
            "What country flag is this", R.drawable.banner,
            "Australia",
            "Argentina",
            "UK",
            "USA",
            3
        )

        val soal7 = Question(
            7,
            "What country flag is this", R.drawable.banner,
            "Scotland",
            "European Union",
            "Armenia",
            "USA",
            2
        )

        val soal8 = Question(
            8,
            "What country flag is this", R.drawable.banner,
            "Netherlands",
            "Spain",
            "Belgium",
            "Germany",
            4
        )

        val soal9 = Question(
            9,
            "What country flag is this", R.drawable.banner,
            "Denmark",
            "Argentina",
            "Canada",
            "USA",
            3
        )

        val soal10 = Question(
            10,
            "What country flag is this", R.drawable.banner,
            "India",
            "Iran",
            "Ireland",
            "USA",
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