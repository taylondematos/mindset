package com.catshouse.sabedoriadiaria2.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun MenuSobreLivros(onSobreBookClick: (String) -> Unit) {

    val listaDeLivros = listOf(
        R.string.aleidotriunfo to "aleidotriunfo",
        R.string.proverbios to "proverbios",
        R.string.ohomemmaisricodababilonia to "ohomemmaisricodababilonia",
        R.string.segredosdamentemilionaria to "segredosdamentemilionaria",
        R.string.pairicopaipobre to "pairicopaipobre",
        R.string.fazeramigos to "fazeramigos",
        R.string.eclesiastes to "eclesiastes",
        R.string.aartedofodase to "aartedofodase",
        R.string.sobreoapp to "sobreoapp"
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.fundopapelvelho),
                contentScale = ContentScale.FillBounds
            )
            .border(width = 1.dp, color = Color.Black, shape = RectangleShape)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .align(alignment = Alignment.Center)
        ) {
            Spacer(modifier = Modifier.weight(0.2f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .background(color = colorResource(id = R.color.corbotaocitacao))
                    .border(5.dp, Color.Black)
            ) {

                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    text = stringResource(id = R.string.sobre),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black, textAlign = TextAlign.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f)
                )
                Spacer(modifier = Modifier.weight(0.5f))

            }

            Spacer(modifier = Modifier.weight(0.05f))
            LazyColumn(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(5f)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(listaDeLivros) { (texto, nomeDoLivro) ->
                    BotaoSobreLivro(
                        texto = texto,
                        onClick = { onSobreBookClick(nomeDoLivro) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Box(modifier = Modifier.weight(1f)) {
                BannerMenuSobreLivros()

            }
        }

    }
}


@Composable
fun BotaoSobreLivro(texto: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 5.dp)
            .border(border = BorderStroke(3.dp, Color.Black), shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(color = colorResource(id = R.color.corbotaocitacao))
            .clickable { onClick() }, contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Info, tint = Color.Black,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)

            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = texto), textAlign = TextAlign.End,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .weight(3f)
                    .padding(5.dp)
            )
        }
    }
}


@Composable
fun BannerMenuSobreLivros() {
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
            adUnitId = "ca-app-pub-6667338739750984/3627833403"
            loadAd(AdRequest.Builder().build())
        }
    })
}
