package com.catshouse.sabedoriadiaria2.helpers

import java.util.Locale

abstract class DetectorDeIdioma {
    companion object{
        /////////////CONFERIR O IDIOMA////////////////////
        fun dectorDeIdioma(): String {
            val currentLocale: Locale = Locale.getDefault()
            return currentLocale.language
        }
    }
}