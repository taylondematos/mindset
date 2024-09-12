package com.catshouse.sabedoriadiaria2.helpers


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.catshouse.sabedoriadiaria2.R

object ShareHelper {


    fun compartilharCitacao(texto: String, autor: String, context: Context) {
        val linkDoApp = "https://bit.ly/frasesmotivacionaisapp"

        val textoMontado = """
            $texto
            
            $autor
            
    ${context.getString(R.string.Paramaisfrasescomoesta)}
    $linkDoApp
        """.trimIndent()

        copiarParaClipBoard(textoMontado, context)

        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, textoMontado)
            type = "text/plain"
        }
        context.startActivity(
            Intent.createChooser(
                intent,
                context.getString(R.string.CompartilharTituloIntent)
            )
        )
    }

    private fun copiarParaClipBoard(textoCopiado: String, context: Context) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textoCopiado)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(
            context,
            context.getString(R.string.TextoCopiadoCasoFacebookColeotexto),
            Toast.LENGTH_LONG
        ).show()
    }
}

