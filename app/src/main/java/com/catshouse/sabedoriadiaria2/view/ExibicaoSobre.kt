package com.catshouse.sabedoriadiaria2.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.window.layout.WindowMetricsCalculator
import com.catshouse.sabedoriadiaria2.R
import com.catshouse.sabedoriadiaria2.viewmodel.ViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ExibicaoSobre(nomeDoLivro: String, viewModel: ViewModel) {

    val context = LocalContext.current
    // Map associando os nomes dos livros aos recursos de string
    val lista = mapOf(
        "aleidotriunfo" to R.string.sobrealeidotriunfo,
        "eclesiastes" to R.string.sobreeclesiastes,
        "fazeramigos" to R.string.sobrefazeramigos,
        "pairicopaipobre" to R.string.sobrepairico,
        "proverbios" to R.string.sobreproverbios,
        "segredosdamentemilionaria" to R.string.sobrementemilionaria,
        "ohomemmaisricodababilonia" to R.string.sobrelivroohomemmaisricodababilonia,
        "aartedofodase" to R.string.sobreaartedofodase,
        "sobreoapp" to R.string.sobreoapptexto
    )

    val texto = context.getString(lista.get(nomeDoLivro) ?: R.string.falha)

    LaunchedEffect(texto) {
        viewModel.carregarInterticial(context)

    }
    Column {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(3f)
        ) {
            Text(
                text = texto,
                Modifier
                    .padding(30.dp)
            )

        }
        Spacer(modifier = Modifier.weight(0.1f))

        BannerSobre()

        Spacer(modifier = Modifier.weight(1f))

    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BannerSobre() {
    AndroidView(factory = {
        val windowMetrics =
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(it)
        val bounds = windowMetrics.bounds

        var adWidthPixels = it.resources.displayMetrics.widthPixels.toFloat()

        if (adWidthPixels == 0f) {
            adWidthPixels = bounds.width().toFloat()
        }
        val density = it.resources.displayMetrics.density

        val adWith = (adWidthPixels / density).toInt()

        AdView(it).apply {
            setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(it, adWith))
            adUnitId = "ca-app-pub-6667338739750984/1091448527"
            loadAd(AdRequest.Builder().build())
        }
    })
}

