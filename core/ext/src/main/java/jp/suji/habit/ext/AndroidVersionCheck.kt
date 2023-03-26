package jp.suji.habit.ext

import android.os.Build

fun isAboveTiramisu(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
}