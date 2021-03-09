package dev.mcd.redditsortof.extensions

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openUrl(url: String): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())

    //noinspection QueryPermissionsNeeded
    return if (packageManager.queryIntentActivities(intent, 0).isNotEmpty()) {
        startActivity(intent)
        true
    } else false
}
