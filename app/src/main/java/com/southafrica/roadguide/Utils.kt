package com.southafrica.roadguide

import android.content.Context
import android.content.Intent
import android.net.Uri

object Utils {
    fun getShareIntent(context: Context): Intent {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.sharing_message))
        sendIntent.type = "text/plain"
        return Intent.createChooser(sendIntent, context.getString(R.string.share_via))
    }

    fun getAppRatingIntent(context: Context): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse(context.getString(R.string.app_play_store_link, context.packageName))
        )
    }
}