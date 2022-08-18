package com.julianparrilla.dragonbooker.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :
    AppCompatActivity() {

    fun hideSoftKeyboard() {
        currentFocus?.let { currentFocus ->
            val inputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, DEFAULT_FLAG)
        }
    }

    companion object {
        const val DEFAULT_FLAG = 0
    }
}
