package uz.hashteam.countriesofworld.util

import android.os.Handler
import android.os.Looper
import android.view.View


fun View?.blockClickable(blockTimeMilles: Long = 200) {
    this?.isEnabled = false
    Looper.myLooper()?.let {
        Handler(it).postDelayed({ this?.isEnabled = true }, blockTimeMilles)
    }
}