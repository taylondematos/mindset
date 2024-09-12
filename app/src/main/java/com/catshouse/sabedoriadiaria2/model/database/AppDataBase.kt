package com.catshouse.sabedoriadiaria2.model.database

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import com.catshouse.sabedoriadiaria2.helpers.DetectorDeIdioma

@Database(
    entities = [
        Aleidotriunfo::class, Eclesiastes::class,
        Fazeramigos::class, Pairicopaipobre::class, Proverbios::class,
        Segredosdamentemilionaria::class, Ohomemmaisricodababilonia::class,
        Aartedofodase::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun aleidotriunfoDAO(): AleidotriunfoDAO
    abstract fun eclesiastesDAO(): EclesiastesDAO
    abstract fun fazeramigosDAO(): FazeramigosDAO
    abstract fun pairicopaipobreDAO(): PairicopaipobreDAO
    abstract fun proverbiosDAO(): ProverbiosDAO
    abstract fun segredosdamentemilionariaDAO(): SegredosdamentemilionariaDAO
    abstract fun ohomemmaisricodababilonia(): OhomemmaisricodababiloniaDAO
    abstract fun aartedofodase(): AartedofodaseDAO


    companion object {
        fun getDataBaseByLanguage(): String {
            val idioma = DetectorDeIdioma.dectorDeIdioma()
            Log.i("a", idioma)

            return when (idioma) {
                "pt" -> "SabedoriaDiariaDataBase.db"
                else -> "SabedoriaDiariaDataBaseEN.db"
            }
        }
    }
}
