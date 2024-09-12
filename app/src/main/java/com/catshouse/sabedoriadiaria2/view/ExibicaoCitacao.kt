package com.catshouse.sabedoriadiaria2.view

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.window.layout.WindowMetricsCalculator
import com.catshouse.sabedoriadiaria2.R
import com.catshouse.sabedoriadiaria2.helpers.LojaHelper
import com.catshouse.sabedoriadiaria2.helpers.ShareHelper
import com.catshouse.sabedoriadiaria2.viewmodel.ViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ExibicaoCitacao(nomeDoLivro: String, viewModel: ViewModel) {

    val context = LocalContext.current
    val texto = viewModel.textoLiveData.observeAsState()
    val autor = viewModel.autorLiveData.observeAsState()
    val contadorCliquesBotaoNovo = viewModel.contadorClickBotaoNovo.observeAsState(0)

    LaunchedEffect(nomeDoLivro) {
        viewModel.getCitacao(nomeDoLivro)
        viewModel.carregarInterticial(context)

        Log.i("NOMEDOLIVRO", nomeDoLivro.toString())
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painter = painterResource(id = R.drawable.fundopapelvelho),
                contentScale = ContentScale.FillBounds
            )
            .border(width = 0.dp, color = Color.Black, shape = RectangleShape)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.5f)

        ) {
            Foto(nomeDoLivro = nomeDoLivro)
        }

        Box(modifier = Modifier.weight(1f)) {
            Banner()
        }

        Spacer(modifier = Modifier.weight(0.2f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .weight(5f)

        ) {
            Citacao(texto.value.toString(), autor.value.toString())
        }
        Spacer(modifier = Modifier.weight(0.2f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .weight(1f), contentAlignment = Alignment.BottomCenter
        ) {
            Botoes(
                nomeDoLivro,
                viewModel,
                texto.value.toString(),
                autor.value.toString(),
                context,
                contadorCliquesBotaoNovo
            )
        }
        Spacer(modifier = Modifier.weight(0.9f))
    }
}


@Composable
fun Foto(nomeDoLivro: String) {
    val foto: Painter = when (nomeDoLivro) {
        "aleidotriunfo" -> {
            painterResource(id = R.drawable.aleidotriunfo)
        }

        "eclesiastes" -> {
            painterResource(id = R.drawable.eclesiastes)
        }

        "fazeramigos" -> {
            painterResource(id = R.drawable.fazeramigos)
        }

        "pairicopaipobre" -> {
            painterResource(id = R.drawable.pairicopaipobre)
        }

        "proverbios" -> {
            painterResource(id = R.drawable.proverbios)
        }

        "segredosdamentemilionaria" -> {
            painterResource(id = R.drawable.segredosdamentemilionaria)
        }

        "ohomemmaisricodababilonia" -> {
            painterResource(id = R.drawable.ohomemmaisricodababilonia)
        }
        "aartedofodase" -> {
            painterResource(id = R.drawable.aartedofodase)
        }

        else -> {
            painterResource(id = R.drawable.aleidotriunfo)
        }
    }
    Image(
        painter = foto,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun Citacao(texto: String, autor: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = texto, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.Black,
            modifier = Modifier
                .padding(10.dp), textAlign = TextAlign.Center
        )

        Text(
            text = autor, fontSize = 20.sp, color = Color.Black,
            modifier = Modifier
                .padding(10.dp), textAlign = TextAlign.Center
        )
    }


}

@Composable
fun Botoes(
    nomeDoLivro: String,
    viewModel: ViewModel,
    texto: String,
    autor: String, context: Context, contadorCliquesBotaoNovo: State<Int>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(
            onClick = { LojaHelper.irParaALoja(context) },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.corbotaocitacao), // Cor de fundo do botão
                contentColor = Color.Black // Cor do texto dentro do botão
            )
        ) {
            Text(
                text = stringResource(id = R.string.botaoLoja),
                textAlign = TextAlign.Center

            )

        }

        Button(
            onClick = {
                viewModel.getCitacao(nomeDoLivro)
                viewModel.incrementarContador()
                if (contadorCliquesBotaoNovo.value >= 3) {
                    viewModel.carregarInterticial(context)
                }

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.corbotaocitacao), // Cor de fundo do botão
                contentColor = Color.Black // Cor do texto dentro do botão
            )
        ) {
            Text(
                text = stringResource(id = R.string.botaoNovaCitacao),
                textAlign = TextAlign.Center
            )
        }


        Button(
            onClick = {
                ShareHelper.compartilharCitacao(texto, autor, context)
                viewModel.carregarInterticial(context)
            },

            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.corbotaocitacao), // Cor de fundo do botão
                contentColor = Color.Black // Cor do texto dentro do botão
            )
        ) {
            Text(
                text = stringResource(id = R.string.botaoCompartilhar),
                textAlign = TextAlign.Center
            )
        }

    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Banner() {
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

