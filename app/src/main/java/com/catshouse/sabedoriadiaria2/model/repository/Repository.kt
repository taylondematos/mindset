package com.catshouse.sabedoriadiaria2.model.repository

import com.catshouse.sabedoriadiaria2.model.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val dataBase: AppDataBase) {

    suspend fun buscarCitacaoDB(livro: String, id: Int): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        return withContext(Dispatchers.IO) {
            when (livro) {
                "aleidotriunfo" -> {
                    val texto = dataBase.aleidotriunfoDAO().getCitacao(id).texto
                    val autor = dataBase.aleidotriunfoDAO().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                "eclesiastes" -> {
                    val texto = dataBase.eclesiastesDAO().getCitacao(id).texto
                    val autor = dataBase.eclesiastesDAO().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                "fazeramigos" -> {
                    val texto = dataBase.fazeramigosDAO().getCitacao(id).texto
                    val autor = dataBase.fazeramigosDAO().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                "pairicopaipobre" -> {
                    val texto = dataBase.pairicopaipobreDAO().getCitacao(id).texto
                    val autor = dataBase.pairicopaipobreDAO().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                "proverbios" -> {
                    val texto = dataBase.proverbiosDAO().getCitacao(id).texto
                    val autor = dataBase.proverbiosDAO().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                "segredosdamentemilionaria" -> {
                    val texto = dataBase.segredosdamentemilionariaDAO().getCitacao(id).texto
                    val autor = dataBase.segredosdamentemilionariaDAO().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                "ohomemmaisricodababilonia" -> {
                    val texto = dataBase.ohomemmaisricodababilonia().getCitacao(id).texto
                    val autor = dataBase.ohomemmaisricodababilonia().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                "aartedofodase" -> {
                    val texto = dataBase.aartedofodase().getCitacao(id).texto
                    val autor = dataBase.aartedofodase().getCitacao(id).autor
                    hashMap.put("texto", texto)
                    hashMap.put("autor", autor)
                }

                else -> {
                    hashMap.put("texto", "Failed")
                    hashMap.put("autor", "Failed")
                }
            }
            return@withContext hashMap
        }

    }
}