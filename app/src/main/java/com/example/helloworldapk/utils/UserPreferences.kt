package com.example.helloworldapk.utils

import android.content.Context
import java.util.UUID

class UserPreferences(private val context: Context) {
    companion object {
        private const val PREFS_NAME = "gamearena_prefs"
        private const val KEY_USER_ID = "user_id"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Flow to observe login state
    val isLoggedIn: kotlinx.coroutines.flow.Flow<Boolean> = kotlinx.coroutines.flow.MutableStateFlow(true) // Mock for now or implement properly if Auth exists

    fun getUserId(): String {
        var userId = prefs.getString(KEY_USER_ID, null)

        if (userId == null) {
            userId = UUID.randomUUID().toString()
            prefs.edit().putString(KEY_USER_ID, userId).apply()
        }

        return userId!!
    }

    fun clearUserSession() {
        prefs.edit().clear().apply()
    }
}
