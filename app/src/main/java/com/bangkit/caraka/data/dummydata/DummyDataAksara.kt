package com.bangkit.caraka.data.dummydata

import com.bangkit.caraka.R
import com.bangkit.caraka.data.database.Artikel
import com.bangkit.caraka.data.database.Kamus
import com.bangkit.caraka.data.database.Question

object DummyDataAksara {

    fun getQuestionQuestionBali(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val soal1 = Question(
            1, "Apa Artinya Aksara Ini?", R.drawable.bali_ga_min, "ha", "na", "ga", "ra", 3
        )

        val soal2 = Question(
            2, "Apa Artinya Aksara Ini?", R.drawable.bali_ka_min, "ya", "ka", "nga", "ba", 2
        )

        val soal3 = Question(
            3, "Apa Artinya Aksara Ini?", R.drawable.bali_ya_min, "nga", "ya", "ka", "pa", 2
        )

        val soal4 = Question(
            4, "Apa Artinya Aksara Ini?", R.drawable.bali_ra_min, "ya", "ba", "nga", "ra", 4
        )

        val soal5 = Question(
            5, "Apa Artinya Aksara Ini?", R.drawable.bali_ja_min, "ja", "ha", "na", "ma", 1
        )

        val soal6 = Question(
            6, "Apa Artinya Aksara Ini?", R.drawable.bali_ma_min, "ba", "ra", "nga", "ma", 4
        )

        val soal7 = Question(
            7, "Apa Artinya Aksara Ini?", R.drawable.bali_ba_min, "na", "ba", "nya", "nga", 2
        )

        val soal8 = Question(
            8, "Apa Artinya Aksara Ini?", R.drawable.bali_nya_min, "ma", "wa", "ba", "nya", 4
        )

        val soal9 = Question(
            9, "Apa Artinya Aksara Ini?", R.drawable.bali_wa_min, "na", "ha", "wa", "ca", 3
        )

        val soal10 = Question(
            10, "Apa Artinya Aksara Ini?", R.drawable.bali_sa_min, "sa", "nga", "ta", "nya", 1
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

    fun getArtikel(): List<Artikel> {
        return listOf(
            Artikel(
                1,
                1,
                "Keindahan dan Keunikan Tari Kecak: Suara dan Gerakan yang Menghanyutkan",
                "https://geti.id/wp-content/uploads/2023/08/image-1024x695.png",
                "Tari Kecak adalah sebuah seni pertunjukan tradisional yang berasal dari Bali, Indonesia, dan terkenal karena tidak melibatkan instrumen musik. Pertunjukan ini fokus pada vokal para penari pria yang duduk melingkar, menciptakan pola suara \"cak\" yang khas dan mengiringi kisah epik Ramayana. Dengan gerakan yang dinamis dan ekspresi wajah yang dramatis, Tari Kecak menghipnotis penonton, membawa mereka ke dalam suasana magis dan mistis. Uniknya, pertunjukan ini juga melibatkan partisipasi langsung penonton, menambahkan elemen interaktif yang memperkaya pengalaman keseluruhan.\n" + "\n" + "Tari Kecak tidak hanya sekadar hiburan, tetapi juga berperan dalam melestarikan warisan budaya Bali. Generasi setelah generasi terus mewarisi dan mempertahankan tradisi ini, menjadikannya bagian integral dari identitas budaya pulau tersebut. Keindahan dan keunikan Tari Kecak tidak hanya terletak pada tata panggung dan koreografi yang memukau, tetapi juga pada kemampuannya menyampaikan cerita Ramayana melalui gabungan suara dan gerakan yang memikat. Sebagai bagian dari kekayaan seni pertunjukan Indonesia, Tari Kecak terus mempesona dan memainkan peran penting dalam menjaga kelestarian budaya.\n"
            ),
            Artikel(
                2,
                1,
                "Tarian Jaipong: Energi, Ritme, dan Kecantikan dari Tanah Sunda",
                "https://asset.kompas.com/crops/ykUf4RFhhRld0PPGg8-YkTiBh2Y=/0x0:650x433/780x390/data/photo/2022/01/06/61d6a7c600546.jpeg",
                "Tarian Jaipong merupakan salah satu warisan budaya Indonesia yang berasal dari Jawa Barat, khususnya daerah Sunda. Merupakan hasil kreasi dari Gugum Gumbira, seorang seniman Indonesia, pada tahun 1961, Jaipong memadukan unsur-unsur tari tradisional Sunda dengan ritme musik modern. Tarian ini menghadirkan keindahan gerakan yang dinamis, musik yang bersemangat, dan keunikan busana yang memukau.\n" +
                        "\n" +
                        "Pertunjukan Jaipong secara visual menarik dengan gerakan yang ekspresif dan penuh energi. Para penari, baik pria maupun wanita, menampilkan langkah-langkah yang khas sambil membentuk pola-pola yang menawan. Gerakan pinggul yang dinamis dan tangan yang bermain dengan indah menciptakan kecantikan tersendiri, mencerminkan kelembutan dan keanggunan.\n" +
                        "\n" +
                        "Musik yang mengiringi Jaipong tak kalah pentingnya. Instrumen seperti kendang, suling, dan kecapi menciptakan ritme yang menggoda untuk menari. Kombinasi antara elemen visual dan auditori menciptakan suasana yang meriah dan memikat penonton. Beberapa pertunjukan Jaipong juga melibatkan interaksi antara penari dan penonton, menambahkan nuansa keakraban dan kegembiraan.\n" +
                        "\n" +
                        "Selain itu, Jaipong turut memperlihatkan kekayaan budaya Sunda melalui busana yang digunakan. Para penari mengenakan pakaian tradisional Sunda yang khas, seperti kebaya, kain batik, dan aksesoris yang mempercantik penampilan mereka. Busana yang dipadukan dengan gerakan tari menciptakan kesan yang memukau dan memperkaya estetika keseluruhan pertunjukan. Tarian Jaipong bukan hanya sekadar hiburan, tetapi juga menjadi simbol keberagaman budaya Indonesia. Seiring berjalannya waktu, Jaipong terus berkembang dan dijaga keasliannya, memperlihatkan keindahan dan kekayaan warisan budaya Indonesia yang patut dilestarikan dan diapresiasi.\n"
            ),
            Artikel(
                3,
                1,
                "Topeng Sekura: Keindahan dan Makna Tarian Tradisional Lampung",
                "https://pariwisataindonesia.id/wp-content/uploads/2020/09/Aksi-Tari-Sekura-pesona.travel-816x544-1.jpg",
                "Tarian tradisional Indonesia terus menghiasi panggung seni dunia, dan di tengah keberagaman tersebut, Tari Topeng Sekura dari Lampung memancarkan pesona uniknya. Mengambil akar dari sejarah kerajaan Lampung, Tari Topeng Sekura merupakan simbol kekayaan budaya dan spiritualitas masyarakat Lampung. Tarian ini melibatkan penari yang memukau dengan gerakan yang mengandung keanggunan dan kecerdasan, sembari memakai topeng yang diukir dengan teliti, menciptakan karakter-karakter mitologis dan roh yang khas.\n" +
                        "\n" +
                        "Topeng dalam Tari Sekura bukan hanya aksesori seni visual, melainkan juga mengandung makna mendalam. Setiap topeng memiliki cerita dan pesan tersendiri, membawa penonton dalam perjalanan yang lebih dari sekadar tontonan. Gerakan tarian yang indah, bersama dengan ekspresi wajah penari, mengomunikasikan makna filosofis dan simbolis yang meresap dalam kehidupan sehari-hari masyarakat Lampung. Dengan demikian, Tari Topeng Sekura tidak sekadar mempersembahkan keindahan visual, tetapi juga mendalam dalam pesan dan makna budayanya.\n" +
                        "\n" +
                        "Keunikan Tari Topeng Sekura semakin diperkuat oleh cerita yang diangkat dalam pertunjukannya. Kisah-kisah mitologis, nilai-nilai kehidupan sehari-hari, dan aspek-aspek keagamaan menjadi inti dari setiap pertunjukan. Tarian ini menjadi suatu bentuk penyampaian pesan yang melekat dalam budaya Lampung, menjadi jendela bagi penonton untuk mengenali dan menghargai kekayaan dan keunikan lokal. Dengan demikian, Topeng Sekura menjadi alat perekat identitas budaya Lampung yang perlu dijaga dan dilestarikan.\n"
            ),
            Artikel(
                4,
                1,
                "Gamelan Degung, Harmoni Etnik dari Jawa Barat",
                "https://asset.kompas.com/crops/CepfdIbuKxx7KwkG1RzNIlxcev8=/39x0:1151x741/750x500/data/photo/2023/01/31/63d90f1460077.jpg",
                "Indonesia, dengan keberagaman budayanya, memiliki sejumlah besar alat musik tradisional yang memperkaya warisan seni suara. Di Jawa Barat, khususnya, Gamelan Degung muncul sebagai perwujudan nyata dari kekayaan musik etnik. Gamelan Degung bukan sekadar serangkaian instrumen musik, melainkan simbol kehidupan dan identitas budaya masyarakat Sunda.\n" +
                        "\n" +
                        "Gamelan Degung terdiri dari berbagai jenis instrumen, termasuk kendang, suling, rebab, gong, dan metallophone seperti bonang dan saron. Setiap instrumen memiliki peran dan karakteristik tertentu, menciptakan harmoni khas yang menggambarkan nuansa dan perasaan dalam setiap pertunjukan. Kendang, sebagai instrumen perkusi utama, memberikan ketukan yang mengatur tempo bagi ansambel, sementara suling dan rebab menambah lapisan melodi yang indah.\n" +
                        "\n" +
                        "Salah satu ciri khas Gamelan Degung adalah penggunaan laras pelog dan slendro, dua sistem tangga nada yang memberikan keunikan tersendiri dalam harmoni musiknya. Melodi yang dihasilkan mencerminkan nuansa tradisional dan mitologis, seringkali disertai oleh vokal yang memperdalam makna dan emosi dari setiap komposisi.\n" +
                        "\n" +
                        "Keberadaan Gamelan Degung bukan hanya sebagai seni pertunjukan semata, melainkan juga sebagai ekspresi budaya yang mendalam. Pertunjukan Gamelan Degung tidak hanya terbatas pada panggung formal, tetapi juga dapat ditemui dalam berbagai upacara adat, upacara keagamaan, dan acara-acara rakyat. Sebagai warisan budaya yang hidup, Gamelan Degung memainkan peran penting dalam menjaga dan merayakan kekayaan musik etnik Sunda, sekaligus menyatu dengan kehidupan sehari-hari masyarakat Jawa Barat.\n"
            ),
            Artikel(
                5,
                1,
                "Gamelan Bali, Eksotisme Harmoni dan Budaya Pulau Dewata",
                "https://www.kintamani.id/wp-content/uploads/gamelan-bali.jpg",
                "Indonesia, dengan keberagaman budayanya, memiliki sejumlah besar alat musik tradisional yang memperkaya warisan seni suara. Di Jawa Barat, khususnya, Gamelan Degung muncul sebagai perwujudan nyata dari kekayaan musik etnik. Gamelan Degung bukan sekadar serangkaian instrumen musik, melainkan simbol kehidupan dan identitas budaya masyarakat Sunda.\n" +
                        "\n" +
                        "Gamelan Degung terdiri dari berbagai jenis instrumen, termasuk kendang, suling, rebab, gong, dan metallophone seperti bonang dan saron. Setiap instrumen memiliki peran dan karakteristik tertentu, menciptakan harmoni khas yang menggambarkan nuansa dan perasaan dalam setiap pertunjukan. Kendang, sebagai instrumen perkusi utama, memberikan ketukan yang mengatur tempo bagi ansambel, sementara suling dan rebab menambah lapisan melodi yang indah.\n" +
                        "\n" +
                        "Salah satu ciri khas Gamelan Degung adalah penggunaan laras pelog dan slendro, dua sistem tangga nada yang memberikan keunikan tersendiri dalam harmoni musiknya. Melodi yang dihasilkan mencerminkan nuansa tradisional dan mitologis, seringkali disertai oleh vokal yang memperdalam makna dan emosi dari setiap komposisi.\n" +
                        "\n" +
                        "Keberadaan Gamelan Degung bukan hanya sebagai seni pertunjukan semata, melainkan juga sebagai ekspresi budaya yang mendalam. Pertunjukan Gamelan Degung tidak hanya terbatas pada panggung formal, tetapi juga dapat ditemui dalam berbagai upacara adat, upacara keagamaan, dan acara-acara rakyat. Sebagai warisan budaya yang hidup, Gamelan Degung memainkan peran penting dalam menjaga dan merayakan kekayaan musik etnik Sunda, sekaligus menyatu dengan kehidupan sehari-hari masyarakat Jawa Barat.\n"
            ),
            Artikel(
                6,
                1,
                "Gambus Lunik Lampung, Eksplorasi Harmoni Tradisional dengan Sentuhan Modern",
                "https://i.ytimg.com/vi/gQGqhldp_NM/maxresdefault.jpg",
                "Provinsi Lampung di Indonesia, selain dikenal dengan keindahan alamnya, juga memiliki warisan seni dan budaya yang kaya. Salah satu wujud kekayaan musik tradisional Lampung adalah Gambus Lunik. Meskipun gambus sendiri berasal dari tradisi Arab, namun di Lampung, alat musik ini mengalami adaptasi yang unik dan menghasilkan suara yang menyatu dengan karakter khas daerah tersebut.\n" +
                        "\n" +
                        "Gambus Lunik adalah varian dari gambus tradisional, yang merupakan alat musik dawai berbentuk pipih yang dimainkan dengan cara dipetik. Di Lampung, Gambus Lunik menjadi semacam inovasi seni musik dengan menyatukan elemen-elemen tradisional dan modern. Dalam proses adaptasinya, Gambus Lunik mempertahankan elemen khas Gambus Arab sambil menambahkan nuansa-nuansa lokal yang mencirikan musik Lampung.\n" +
                        "\n" +
                        "Gambus Lunik Lampung menciptakan harmoni yang unik antara melodi tradisional dan sentuhan modern. Di samping penggunaan dawai yang khas pada gambus, musisi sering menambahkan elemen modern seperti ritme dan instrumen tambahan. Gabungan ini menciptakan nuansa yang mendalam dan mencirikan identitas musik Lampung yang berkembang dengan dinamika zaman.\n" +
                        "\n" +
                        "Gambus Lunik sering dihadirkan dalam berbagai pertunjukan seni, acara rakyat, dan upacara adat di Lampung. Musik yang dihasilkan menciptakan suasana yang hangat dan menggugah, menarik perhatian penonton dari berbagai lapisan masyarakat. Dalam perkembangannya, Gambus Lunik terus mempertahankan relevansinya di tengah arus modernisasi, menjadi bukti bahwa seni musik tradisional dapat berkembang dan tetap memikat di era kontemporer. Sebagai simbol harmoni antara masa lalu dan sekarang, Gambus Lunik terus menjadi elemen penting dalam memelihara dan merayakan kekayaan seni musik Lampung.\n"
            ),
        )
    }
}