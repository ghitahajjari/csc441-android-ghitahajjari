package edu.lemoyne.campusapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

// Class 22: Step 4: a separate simple loginscreen
@Composable
fun LoginScreen(
    state: LoginUiState,
    onLogin: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize().padding(top=48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email, onValueChange = { email=it },
            label = { Text("Email") }, singleLine = true
        )
        OutlinedTextField(
            value = password, onValueChange = { password=it },
            label = { Text("Password") }, singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = { onLogin(email, password) },
            enabled = !state.loading
        ) { Text("Login") }
        if(state.failed) {
            Text("Login failed", color = MaterialTheme.colorScheme.error)
        }
    }
}