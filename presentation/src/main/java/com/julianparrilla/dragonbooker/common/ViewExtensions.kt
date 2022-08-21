package com.julianparrilla.dragonbooker.common

import android.app.Activity
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.julianparrilla.dragonbooker.R

fun Activity.displayToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

var materialDialog: MaterialDialog? = null

fun Activity.displaySuccessDialog(message: String) {
    materialDialog = MaterialDialog(this)
        .show {
            title(R.string.text_success)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.displayErrorDialog(message: String) {
    materialDialog = MaterialDialog(this)
        .show {
            title(R.string.text_error)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}

fun Activity.displayInfoDialog(message: String?) {
    materialDialog = MaterialDialog(this)
        .show {
            title(R.string.text_info)
            message(text = message)
            positiveButton(R.string.text_ok)
        }
}
