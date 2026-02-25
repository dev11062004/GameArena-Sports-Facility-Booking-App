package com.example.gamearena.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamearena.ui.components.GameArenaButton
import com.example.gamearena.ui.components.GameArenaTextField
import com.example.gamearena.ui.viewmodel.AuthState
import com.example.gamearena.ui.viewmodel.AuthViewModel
import com.example.gamearena.utils.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    onRegistrationSuccess: () -> Unit,
    onBackClick: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val scope = rememberCoroutineScope()

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    
    // Gender Selection (Radio Buttons)
    val radioOptions = listOf("Male", "Female", "Other")
    var selectedGender by remember { mutableStateOf(radioOptions[0]) }

    // Terms (Checkbox)
    var termsAccepted by remember { mutableStateOf(false) }

    var localErrorMessage by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()

    val authState by authViewModel.authState.collectAsState()
    val currentUser by authViewModel.currentUser.collectAsState()

    // Handle authentication state changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                // Save user session
                currentUser?.let { user ->
                    userPreferences.saveUserSession(
                        email = user.email ?: "",
                        name = user.displayName ?: "",
                        uid = user.uid
                    )
                }
                onRegistrationSuccess()
                authViewModel.resetAuthState()
            }
            else -> { /* Handle other states in UI */ }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                enabled = authState !is AuthState.Loading
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Full Name
        GameArenaTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Full Name",
            leadingIcon = Icons.Default.Person,
            enabled = authState !is AuthState.Loading,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email
        GameArenaTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            leadingIcon = Icons.Default.Email,
            enabled = authState !is AuthState.Loading,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        GameArenaTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            enabled = authState !is AuthState.Loading,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password
        GameArenaTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            enabled = authState !is AuthState.Loading,
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Gender Selection
        Text(
            text = "Gender",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == selectedGender),
                            onClick = { selectedGender = text },
                            role = Role.RadioButton,
                            enabled = authState !is AuthState.Loading
                        )
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedGender),
                        onClick = null,
                        enabled = authState !is AuthState.Loading
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Terms and Conditions
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = { termsAccepted = it },
                enabled = authState !is AuthState.Loading
            )
            Text(
                text = "I agree to the Terms & Conditions",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable(enabled = authState !is AuthState.Loading) {
                    termsAccepted = !termsAccepted
                }
            )
        }

        // Error messages
        val errorToShow = localErrorMessage ?: (authState as? AuthState.Error)?.message
        if (errorToShow != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorToShow,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sign Up Button
        GameArenaButton(
            text = if (authState is AuthState.Loading) "Creating Account..." else "Sign Up",
            onClick = {
                localErrorMessage = null

                // Client-side validation
                when {
                    fullName.isBlank() || email.isBlank() || password.isBlank() ->
                        localErrorMessage = "Please fill in all fields"
                    password != confirmPassword ->
                        localErrorMessage = "Passwords do not match"
                    !termsAccepted ->
                        localErrorMessage = "Please accept Terms & Conditions"
                    else -> {
                        // Call Firebase registration
                        authViewModel.registerUser(email, password, fullName)
                    }
                }
            },
            enabled = authState !is AuthState.Loading
        )

        // Loading indicator
        if (authState is AuthState.Loading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
