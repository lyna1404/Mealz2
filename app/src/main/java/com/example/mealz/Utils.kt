package com.example.mealz

import android.content.Context
import android.content.Intent
import android.net.Uri

fun sendEmail(ctx: Context, receiver:String){
    val intent = Intent(Intent.ACTION_SEND)
    intent.data = Uri.parse("mailto:")
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
    ctx.startActivity(intent)
}

fun openPage(ctx: Context, url: String, weburl : String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ctx.startActivity(intent)

    } catch (e: android.content.ActivityNotFoundException) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(weburl))
        ctx.startActivity(intent)
    }
}