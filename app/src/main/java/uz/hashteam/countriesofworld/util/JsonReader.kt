package uz.hashteam.countriesofworld.util

import android.content.Context
import java.io.IOException

/**
 * Created by Shohjahon Sirojev on 6/27/2020.
 * Company #:TEAM
 * Email shohjahon0024@gmail.com
 */

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
