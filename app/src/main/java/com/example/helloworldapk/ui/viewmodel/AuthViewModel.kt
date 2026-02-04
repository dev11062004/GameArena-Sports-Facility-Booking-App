package com.example.helloworldapk.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldapk.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for handling authentication operations
 */
class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository()
) : ViewModel() {

    // Authentication state
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    // Current user
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser.asStateFlow()

    init {
        // Check if user is already logged in
        checkAuthState()
    }

    /**
     * Check current authentication state
     */
    private fun checkAuthState() {
        _currentUser.value = authRepository.currentUser
        if (authRepository.isUserLoggedIn) {
            _authState.value = AuthState.Authenticated
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    /**
     * Register new user
     */
    fun registerUser(email: String, password: String, fullName: String) {
        // Validate inputs
        if (fullName.isBlank()) {
            _authState.value = AuthState.Error("Please enter your full name")
            return
        }

        if (fullName.length < 2) {
            _authState.value = AuthState.Error("Name must be at least 2 characters")
            return
        }

        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Please enter a valid email address")
            return
        }

        if (password.length < 6) {
            _authState.value = AuthState.Error("Password must be at least 6 characters")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = authRepository.registerUser(email, password, fullName)
            result.fold(
                onSuccess = { user ->
                    _currentUser.value = user
                    _authState.value = AuthState.Authenticated
                },
                onFailure = { exception ->
                    _authState.value = AuthState.Error(parseFirebaseError(exception))
                }
            )
        }
    }

    /**
     * Login user
     */
    fun loginUser(email: String, password: String) {
        // Validate inputs
        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Please enter a valid email address")
            return
        }

        if (password.isBlank()) {
            _authState.value = AuthState.Error("Please enter your password")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = authRepository.loginUser(email, password)
            result.fold(
                onSuccess = { user ->
                    _currentUser.value = user
                    _authState.value = AuthState.Authenticated
                },
                onFailure = { exception ->
                    _authState.value = AuthState.Error(parseFirebaseError(exception))
                }
            )
        }
    }

    /**
     * Logout user
     */
    fun logoutUser() {
        authRepository.logoutUser()
        _currentUser.value = null
        _authState.value = AuthState.Unauthenticated
    }

    /**
     * Send password reset email
     */
    fun sendPasswordReset(email: String) {
        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("Please enter a valid email address")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val result = authRepository.sendPasswordResetEmail(email)
            result.fold(
                onSuccess = {
                    _authState.value = AuthState.PasswordResetSent
                },
                onFailure = { exception ->
                    _authState.value = AuthState.Error(parseFirebaseError(exception))
                }
            )
        }
    }

    /**
     * Reset auth state to initial
     */
    fun resetAuthState() {
        _authState.value = if (authRepository.isUserLoggedIn) {
            AuthState.Authenticated
        } else {
            AuthState.Unauthenticated
        }
    }

    /**
     * Validate email format
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Parse Firebase error messages to user-friendly messages
     */
    private fun parseFirebaseError(exception: Throwable): String {
        return when {
            exception.message?.contains("There is no user record") == true ->
                "No account found with this email"
            exception.message?.contains("password is invalid") == true ->
                "Incorrect password"
            exception.message?.contains("email address is already in use") == true ->
                "Email already registered"
            exception.message?.contains("The email address is badly formatted") == true ->
                "Invalid email format"
            exception.message?.contains("weak password") == true ->
                "Password should be at least 6 characters"
            exception.message?.contains("network error") == true ->
                "Check your internet connection"
            exception.message?.contains("too many requests") == true ->
                "Too many attempts. Please try again later"
            else -> exception.message ?: "Authentication failed. Please try again"
        }
    }
}

/**
 * Authentication state sealed class
 */
sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object PasswordResetSent : AuthState()
    data class Error(val message: String) : AuthState()
}
