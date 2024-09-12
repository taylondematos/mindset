import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.catshouse.sabedoriadiaria2.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ADsHelper {

    private var mInterstitialAd: InterstitialAd? = null
    private val TAG = "INTERTITIALADMOB"

    suspend fun carregarInterticial(context: Context): Boolean {
        val scope = CoroutineScope(Dispatchers.Main)
        val retorno = scope.async {
            val adRequest = AdRequest.Builder().build()
            val intertitial = "ca-app-pub-6667338739750984/4943063620"

            InterstitialAd.load(
                context,
                intertitial,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d(TAG, "add not loaded: ${adError.message}")
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        Log.d(TAG, "Ad was loaded.")
                        mInterstitialAd = interstitialAd
                        scope.async {
                            mostrarInterticial(context)
                        }
                    }
                })

            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    mInterstitialAd = null
                }
            }
            val meuTexto = context.getString(R.string.avisoAds)
            Toast.makeText(context, meuTexto, Toast.LENGTH_LONG).show()
            return@async true
        }
        return retorno.await()
    }

    suspend fun mostrarInterticial(context: Context): Boolean {
        val scope = CoroutineScope(Dispatchers.Main)
        val retorno = scope.async {
            if (mInterstitialAd != null) {
                Log.d(TAG, "Ad NOT NULL.")
                mInterstitialAd?.show(context as Activity)
                return@async false
            } else {
                Log.d(TAG, "Ad NULL.")
                return@async true
            }

        }
        return retorno.await()
    }

}
