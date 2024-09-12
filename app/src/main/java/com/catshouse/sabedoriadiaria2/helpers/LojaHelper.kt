package com.catshouse.sabedoriadiaria2.helpers

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.catshouse.sabedoriadiaria2.R

object LojaHelper {

    fun irParaALoja(context: Context) {

        val idioma = DetectorDeIdioma.dectorDeIdioma()
        var urlLojaPortugues = "https://shoplinks.to/MinutosDeSabedoriaLoja"
        var urlLojaIngles = "https://shoplinks.to/MindsetStore"

        when (idioma) {
            "pt" -> dialogoLoja(idioma, urlLojaPortugues, context)
            else -> dialogoLoja(idioma, urlLojaIngles, context)
        }
    }

    private fun dialogoLoja(idioma: String, urlLoja: String, context: Context) {
        var titulo = context.getString(R.string.titulovisitarloja)
        var mensagem = context.getString(R.string.mensagemvisitarloja)
        var sim = context.getString(R.string.botaosim)
        var nao = context.getString(R.string.botaonao)


        /*
                when (idioma) {
            "pt" -> {
            titulo = "Visitar a Loja"
            mensagem = "A loja será aberta em seu navegador"
            sim = "SIM"
            nao = "Não"
        }

            else -> {
            titulo = "Visit the Store"
            mensagem = "The store will be opened in your browser"
            sim = "YES"
            nao = "No"
        }
        }

         */
        val builder = AlertDialog.Builder(context)
        builder.setMessage(mensagem)
            .setPositiveButton(sim) { dialog, id ->
                abrirLink(urlLoja, context)
            }
            .setNegativeButton(nao) { dialog, id ->
                dialog.dismiss()
            }
            .setIcon(R.drawable.livroabertura)
            .setTitle(titulo)

        builder.create().show()
    }

    private fun abrirLink(url: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }

}