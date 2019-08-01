package artezyazilim.com.addapp

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
var activeContext: Context? = null

fun VeriKaydet(xStr: String, key: String, context: Context = activeContext!!) {
    try{
        val sharedPreferences = context.getSharedPreferences(context.packageName, android.content.Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, xStr).apply()
    }catch (e: Exception) {
    }
}

fun KayitliVeri(key: String, context: Context = activeContext!!): String {
    val sharedPreferences = context.getSharedPreferences(context.packageName, android.content.Context.MODE_PRIVATE)

    return sharedPreferences.getString(key, "")
}