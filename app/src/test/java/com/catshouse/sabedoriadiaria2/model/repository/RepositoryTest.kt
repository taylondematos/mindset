package com.catshouse.sabedoriadiaria2.model.repository

import android.content.Context
import com.catshouse.sabedoriadiaria2.model.Citacao
import com.catshouse.sabedoriadiaria2.model.database.AleidotriunfoDAO
import com.catshouse.sabedoriadiaria2.model.database.AppDataBase
import com.catshouse.sabedoriadiaria2.model.database.EclesiastesDAO
import com.catshouse.sabedoriadiaria2.model.database.FazeramigosDAO
import com.catshouse.sabedoriadiaria2.model.database.PairicopaipobreDAO
import com.catshouse.sabedoriadiaria2.model.database.ProverbiosDAO
import com.catshouse.sabedoriadiaria2.model.database.SegredosdamentemilionariaDAO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class RepositoryTest {

    private lateinit var repository: Repository
    private lateinit var context: Context
    private lateinit var mockDataBase: AppDataBase
    private lateinit var mockAleidotriunfoDAO: AleidotriunfoDAO
    private lateinit var mockEclesiastesDAO: EclesiastesDAO
    private lateinit var mockFazeramigosDAO: FazeramigosDAO
    private lateinit var mockPairicopaipobreDAO: PairicopaipobreDAO
    private lateinit var mockProverbiosDAO: ProverbiosDAO
    private lateinit var mockSegredosdamentemilionariaDAO: SegredosdamentemilionariaDAO


    @Before
    fun setUp() {

        mockDataBase = mock(AppDataBase::class.java)
        context = RuntimeEnvironment.getApplication().applicationContext
        mockAleidotriunfoDAO = mock(AleidotriunfoDAO::class.java)
        mockEclesiastesDAO = mock(EclesiastesDAO::class.java)
        mockFazeramigosDAO = mock(FazeramigosDAO::class.java)
        mockPairicopaipobreDAO = mock(PairicopaipobreDAO::class.java)
        mockProverbiosDAO = mock(ProverbiosDAO::class.java)
        mockSegredosdamentemilionariaDAO = mock(SegredosdamentemilionariaDAO::class.java)

        `when`(mockDataBase.aleidotriunfoDAO()).thenReturn(mockAleidotriunfoDAO)
        `when`(mockDataBase.eclesiastesDAO()).thenReturn(mockEclesiastesDAO)
        `when`(mockDataBase.fazeramigosDAO()).thenReturn(mockFazeramigosDAO)
        `when`(mockDataBase.pairicopaipobreDAO()).thenReturn(mockPairicopaipobreDAO)
        `when`(mockDataBase.proverbiosDAO()).thenReturn(mockProverbiosDAO)
        `when`(mockDataBase.segredosdamentemilionariaDAO()).thenReturn(
            mockSegredosdamentemilionariaDAO
        )

        repository = Repository(mockDataBase)
    }

    @After
    fun tearDown() {
        mockDataBase.close()
    }

    @Test
    fun testBuscarCitacaoDB_aleidotriunfo() = runTest {
        val id = 1
        val citacao = Citacao(id, "Texto Exemplo", "Autor Exemplo")
        `when`(mockAleidotriunfoDAO.getCitacao(id)).thenReturn(citacao)

        val result = repository.buscarCitacaoDB("aleidotriunfo", id)

        assertEquals("Texto Exemplo", result.get("texto"))
    }

    @Test
    fun testBuscarCitacaoDB_eclesiastes() = runTest {
        val id = 1
        val citacao = Citacao(id, "Texto Exemplo", "Autor Exemplo")
        `when`(mockEclesiastesDAO.getCitacao(id)).thenReturn(citacao)

        val result = repository.buscarCitacaoDB("eclesiastes", id)

        assertEquals("Texto Exemplo", result.get("texto"))
    }

    @Test
    fun testBuscarCitacaoDB_fazeramigos() = runTest {
        val id = 1
        val citacao = Citacao(id, "Texto Exemplo", "Autor Exemplo")
        `when`(mockFazeramigosDAO.getCitacao(id)).thenReturn(citacao)

        val result = repository.buscarCitacaoDB("fazeramigos", id)

        assertEquals("Texto Exemplo", result.get("texto"))
    }

    @Test
    fun testBuscarCitacaoDB_pairicopaipobre() = runTest {
        val id = 1
        val citacao = Citacao(id, "Texto Exemplo", "Autor Exemplo")
        `when`(mockPairicopaipobreDAO.getCitacao(id)).thenReturn(citacao)

        val result = repository.buscarCitacaoDB("pairicopaipobre", id)

        assertEquals("Texto Exemplo", result.get("texto"))
    }

    @Test
    fun testBuscarCitacaoDB_segredosdamentemilionaria() = runTest {
        val id = 1
        val citacao = Citacao(id, "Texto Exemplo", "Autor Exemplo")
        `when`(mockSegredosdamentemilionariaDAO.getCitacao(id)).thenReturn(citacao)

        val result = repository.buscarCitacaoDB("segredosdamentemilionaria", id)

        assertEquals("Texto Exemplo", result.get("texto"))
    }

}