package com.example.gamearena.utils

import android.content.Context
import java.util.UUID

class UserPreferences(private val context: Context) {
    companion object {
        private const val PREFS_NAME = "gamearena_prefs"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_UID = "user_uid"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
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

    fun saveUserSession(email: String, name: String, uid: String) {
        prefs.edit().apply {
            putString(KEY_USER_EMAIL, email)
            putString(KEY_USER_NAME, name)
            putString(KEY_USER_UID, uid)
            putBoolean(KEY_IS_LOGGED_IN, true)
            apply()
        }
    }

    fun getUserEmail(): String? = prefs.getString(KEY_USER_EMAIL, null)
    fun getUserName(): String? = prefs.getString(KEY_USER_NAME, null)
    fun getUserUid(): String? = prefs.getString(KEY_USER_UID, null)
    fun isUserLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun clearUserSession() {
        prefs.edit().clear().apply()
    }
}
