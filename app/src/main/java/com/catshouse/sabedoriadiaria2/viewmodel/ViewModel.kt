package com.catshouse.sabedoriadiaria2.viewmodel

import ADsHelper
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catshouse.sabedoriadiaria2.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var textoLiveData = MutableLiveData<String>()
        private set

    var autorLiveData = MutableLiveData<String>()
        private set

    private lateinit var adsHelper: ADsHelper

    private var interticialMutableLiveData = MutableLiveData<Boolean>()

    var contadorClickBotaoNovo = MutableLiveData(0)
        private set


    fun getCitacao(livro: String, id: Int = criarIdRandom(livro)) {
        viewModelScope.launch {
            val hashMap = repository.buscarCitacaoDB(livro, id)
            val texto = hashMap["texto"]
            val autor = hashMap["autor"]
            textoLiveData.value = texto ?: ""
            autorLiveData.value = autor ?: ""
        }
    }

    fun carregarInterticial(context: Context) {
        viewModelScope.launch {
            adsHelper = ADsHelper()
            interticialMutableLiveData.value = adsHelper.carregarInterticial(context)

            Log.d("INTERTITIALADMOB", interticialMutableLiveData.value.toString())

            if (interticialMutableLiveData.value == true) {
                interticialMutableLiveData.value =
                    adsHelper.mostrarInterticial(context)
                zerarContador()
            }

        }
    }

    private fun criarIdRandom(livro: String): Int {
        val geradorDeRandom: (IntRange) -> Int = { intRange ->
            Random.nextInt(intRange.first, intRange.last + 1)
        }
        viewModelScope.launch {

        }
        return when (livro) {
            "aleidotriunfo" -> {
                geradorDeRandom(1..203)
            }

            "eclesiastes" -> {
                geradorDeRandom(1..47)
            }

            "fazeramigos" -> {
                geradorDeRandom(1..84)
            }

            "pairicopaipobre" -> {
                geradorDeRandom(1..119)
            }

            "proverbios" -> {
                geradorDeRandom(1..173)
            }

            "segredosdamentemilionaria" -> {
                geradorDeRandom(1..192)
            }

            "ohomemmaisricodababilonia" -> {
                geradorDeRandom(1..57)
            }

            "aartedofodase" -> {
                geradorDeRandom(1..65)
            }

            else -> {
                geradorDeRandom(1..47)

            }
        }

    }

    fun incrementarContador() {
        contadorClickBotaoNovo.value = (contadorClickBotaoNovo.value ?: 0) + 1
        Log.d("INTERTITIALADMOB", contadorClickBotaoNovo.value.toString())

    }

    private fun zerarContador() {
        contadorClickBotaoNovo.value = 0
    }
}