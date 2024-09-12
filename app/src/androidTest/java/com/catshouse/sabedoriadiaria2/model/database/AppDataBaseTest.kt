package com.catshouse.sabedoriadiaria2.model.database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDataBaseTest {

    private lateinit var db: AppDataBase
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        db= DatabaseModule.provideDatabase(context)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun aleidotriunfoDAO() {
        val textoDB = db.aleidotriunfoDAO().getCitacao(1).texto
        val texto =
            "Men and women who have achieved great" +
                    " success in life have had to correct" +
                    " certain weaknesses in their personalities" +
                    " before embarking on the road to triumph."
        assertEquals(texto, textoDB)
    }

    @Test
    fun eclesiastesDAO() {
        val textoDB = db.eclesiastesDAO().getCitacao(1).texto
        val texto = "What profit has a man from all his labor, in which he toils under the sun?\n"


        assertEquals(texto, textoDB)
    }

    @Test
    fun fazeramigosDAO() {
        val textoDB = db.fazeramigosDAO().getCitacao(1).texto
        val texto =
            "Always remind yourself: \"My popularity, my happiness, and my sense of worth depend primarily on my ability to treat people well.\""


        assertEquals(texto, textoDB)
    }

    @Test
    fun pairicopaipobreDAO() {
        val textoDB = db.pairicopaipobreDAO().getCitacao(1).texto
        val texto =
            "The only way to escape the \"Rat Race\" is to prove your proficiency in both accounting and investment."
        assertEquals(texto, textoDB)
    }

    @Test
    fun proverbiosDAO() {
        val textoDB = db.proverbiosDAO().getCitacao(1).texto
        val texto =
            "The wise will hear and increase learning, and the understanding will acquire wise counsel;"
        assertEquals(texto, textoDB)
    }

    @Test
    fun segredosdamentemilionariaDAO() {
        val textoDB = db.segredosdamentemilionariaDAO().getCitacao(1).texto
        val texto =
            "This is not an exact science, but almost all wealthy people think completely differently from others.\n" +
                    "\n"

        assertEquals(texto, textoDB)
    }
}