package com.astrick.core.extensions

import android.app.Activity
import android.view.WindowManager

fun Activity.preventKeyboardFromPushingUpUi() {
    window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
}
