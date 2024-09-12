package com.catshouse.sabedoriadiaria2.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//OS NOMES DAS ENTIDADES ESTÃO EM MINUSCULO POIS SÃO COMO ESTÃO NO DATABASE ORIGINAL, QUE É PROPULADO

@Entity(tableName = "aleidotriunfo")
data class Aleidotriunfo(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)

@Entity(tableName = "eclesiastes")
data class Eclesiastes(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)

@Entity(tableName = "fazeramigos")
data class Fazeramigos(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)

@Entity(tableName = "pairicopaipobre")
data class Pairicopaipobre(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)

@Entity(tableName = "proverbios")
data class Proverbios(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)

@Entity(tableName = "segredosdamentemilionaria")
data class Segredosdamentemilionaria(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)

@Entity(tableName = "ohomemmaisricodababilonia")
data class Ohomemmaisricodababilonia(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)

@Entity(tableName = "aartedofodase")
data class Aartedofodase (
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "texto") val texto: String,
    @ColumnInfo(name = "autor") val autor: String
)
