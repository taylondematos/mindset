package com.catshouse.sabedoriadiaria2.helpers

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

object PermissaoPushNotificationHelper {

    private var requestPermissionLauncher: ActivityResultLauncher<String>? = null

    // Declare the launcher at the top of your Activity/Fragment:
    fun registerPermissionLauncher(activity: ComponentActivity) {
        requestPermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // Handle the case where the user denies the permission
            }
        }
    }


    fun askNotificationPermission(activity: ComponentActivity) {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (activity.shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                } else {
                // Directly ask for the permission
                requestPermissionLauncher?.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}
