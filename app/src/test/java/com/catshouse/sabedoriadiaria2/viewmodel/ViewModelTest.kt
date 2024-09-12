package com.catshouse.sabedoriadiaria2.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.catshouse.sabedoriadiaria2.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: ViewModel
    private lateinit var repository: Repository
    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set the test dispatcher as Main
        repository = mock(Repository::class.java)
        viewModel = ViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testeConteudoLiveData() = runTest {
        val hashMap = HashMap<String, String>()
        hashMap.put("texto", "textoResultado")
        hashMap.put("autor", "autorResultado")

        `when`(
            repository.buscarCitacaoDB("aleidotriunfo", 10)
        ).thenReturn(hashMap)

        viewModel.getCitacao("aleidotriunfo",10)

        assertEquals(hashMap["texto"], viewModel.textoLiveData.value)
    }
}