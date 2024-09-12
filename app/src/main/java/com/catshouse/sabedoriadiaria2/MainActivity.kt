package com.catshouse.sabedoriadiaria2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catshouse.sabedoriadiaria2.helpers.PermissaoPushNotificationHelper
import com.catshouse.sabedoriadiaria2.ui.theme.MinutesOfWisdomTheme
import com.catshouse.sabedoriadiaria2.view.ExibicaoCitacao
import com.catshouse.sabedoriadiaria2.view.ExibicaoSobre
import com.catshouse.sabedoriadiaria2.view.MenuPrincipal
import com.catshouse.sabedoriadiaria2.view.MenuSobreLivros
import com.catshouse.sabedoriadiaria2.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        permissaoPushNotification(this)
        setContent {
            MinutesOfWisdomTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "menuPrincipal") {
                        composable("menuPrincipal") {
                            MenuPrincipal(
                                onBookClick = { nomeDoLivro -> navController.navigate("exibicaoCitacao/$nomeDoLivro") },
                                onMenuSobreLivrosClick = { navController.navigate("menuSobrelivros") }
                            )
                        }
                        composable("menuSobrelivros") {
                            MenuSobreLivros(
                                onSobreBookClick = { nomeDoLivro -> navController.navigate("exibicaoSobre/$nomeDoLivro") }

                            )
                        }
                        composable("exibicaoCitacao/{nomeDoLivro}") { backStackEntry ->
                            val nomeDoLivro = backStackEntry.arguments?.getString("nomeDoLivro")
                            ExibicaoCitacao(
                                nomeDoLivro.toString(),
                                viewModel
                            )
                        }
                        composable("exibicaoSobre/{nomeDoLivro}") { backStackEntry ->
                            val nomeDoLivro = backStackEntry.arguments?.getString("nomeDoLivro")
                            ExibicaoSobre(
                                nomeDoLivro.toString(), viewModel
                            )
                        }
                    }
                }

            }
        }
    }

    private fun permissaoPushNotification(activity: ComponentActivity) {
        PermissaoPushNotificationHelper.registerPermissionLauncher(activity)
        PermissaoPushNotificationHelper.askNotificationPermission(activity)
    }
}


