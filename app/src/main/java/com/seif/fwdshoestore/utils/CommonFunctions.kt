package com.seif.fwdshoestore.utils


import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.google.android.material.snackbar.Snackbar
import com.seif.booksislandapp.utils.Resource
import java.util.regex.Pattern

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.disable() {
    isEnabled = false
}

fun View.enabled() {
    isEnabled = true
}

fun isValidEmailAndPassword(email: String, password: String): Resource<String, String> {
    return if (email.isEmpty()) {
        Resource.Error("email can't be empty !")
    } else if (!isValidEmailFormat(email)) {
        Resource.Error("please enter a valid email !")
    } else if (password.isEmpty()) {
        Resource.Error("password can't be empty !")
    } else if (!isValidPasswordFormat(password)) {
        Resource.Error("please enter a valid password !")
    } else {
        Resource.Success("valid User")
    }
}


fun isValidEmailFormat(email: String): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPasswordFormat(password: String): Boolean {
    val passwordRegex = Pattern.compile(
        "^" +
            "(?=.*[0-9])" + // at least 1 digit
            "(?=.*[a-z])" + // at least 1 lower case letter
            "(?=.*[A-Z])" + // at least 1 upper case letter
            "(?=.*[@#$%^&+=])" + // at least 1 special character
            "(?=\\S+$)" + // no white spaces
            ".{6,}" + // at least 6 characters
            "$"
    )
    return passwordRegex.matcher(password).matches()
}
