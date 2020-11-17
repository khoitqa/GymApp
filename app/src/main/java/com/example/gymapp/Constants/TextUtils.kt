package com.example.gymapp.Constants

import android.content.Context
import android.widget.Toast

class TextUtils {
    companion object {
        fun emptyCheck(context: Context, text: String, tag: String): Boolean {
            if (text.isEmpty()) {
                Toast.makeText(
                    context,
                    tag + " can't be empty, please enter again.",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }
            return true
        }

        fun emailValid(context: Context, email: String): Boolean {

            val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true
            } else {
                Toast.makeText(
                    context, "Invalid email address",
                    Toast.LENGTH_SHORT
                ).show()
            }
            return false
        }

        fun showToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }

    }
}