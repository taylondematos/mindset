package com.catshouse.sabedoriadiaria2.model.database

import androidx.room.Dao
import androidx.room.Query
import com.catshouse.sabedoriadiaria2.model.Citacao

@Dao
interface AleidotriunfoDAO {
    @Query("SELECT * FROM Aleidotriunfo Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}

@Dao
interface EclesiastesDAO {
    @Query("SELECT * FROM Eclesiastes Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}

@Dao
interface FazeramigosDAO {
    @Query("SELECT * FROM Fazeramigos Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}

@Dao
interface PairicopaipobreDAO {
    @Query("SELECT * FROM Pairicopaipobre Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}

@Dao
interface ProverbiosDAO {
    @Query("SELECT * FROM Proverbios Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}

@Dao
interface SegredosdamentemilionariaDAO {
    @Query("SELECT * FROM Segredosdamentemilionaria Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}

@Dao
interface OhomemmaisricodababiloniaDAO {
    @Query("SELECT * FROM Ohomemmaisricodababilonia Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}

@Dao
interface AartedofodaseDAO {
    @Query("SELECT * FROM Aartedofodase Where id = :id ")
    fun getCitacao(id: Int) : Citacao
}