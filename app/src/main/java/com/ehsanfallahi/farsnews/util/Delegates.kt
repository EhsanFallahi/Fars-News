package com.ehsanfallahi.farsnews.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ehsanfallahi.farsnews.R
import kotlinx.coroutines.*

fun<T>lazyDeferred(block:suspend CoroutineScope.()->T):Lazy<Deferred<T>>{
    return lazy{
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}

fun shareNewsLink(link: String, context: Context){
    val sendIntent: Intent = Intent().apply {
        flags= Intent.FLAG_ACTIVITY_NEW_TASK
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }
    Intent.createChooser(sendIntent, null)
    ContextCompat.startActivity(context, sendIntent, null)
}

fun copyToClipboard(text: CharSequence,context: Context){
    val clipboard=context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    ContextCompat.getSystemService(context, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("label",text)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, context.getString(R.string.text_for_copy), Toast.LENGTH_SHORT).show();
}
