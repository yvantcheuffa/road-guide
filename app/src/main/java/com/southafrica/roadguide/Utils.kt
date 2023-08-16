package com.southafrica.roadguide

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.style.UnderlineSpan

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

    fun getJoinOnFacebookIntent(context: Context): Intent {
        return try {
            context.packageManager.getPackageInfo("com.facebook.katana", 0)
            Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.facebook_page_id)))
        } catch (e: Exception) {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/<user_name_here>"))
        }
    }

    fun getUnderlinedText(text: String): SpannableString {
        val content = SpannableString(text)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        return content
    }
}