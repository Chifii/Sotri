package io.challenge.stori.login.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.challenge.stori.R
import io.challenge.stori.login.view.ui.theme.StoriTheme
import io.challenge.stori.login.viewModel.LoginViewModel

class LoginActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			StoriTheme {
				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
				) {
					LoginScreen(LoginViewModel())
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
	loginViewModel: LoginViewModel
) {
	val context = LocalContext.current
	val isAuthenticated by loginViewModel.isAuthenticated.observeAsState()

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	val isLoginButtonEnabled = email.isNotEmpty() && password.isNotEmpty()

	if (isAuthenticated == true) {
		Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
	}

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = painterResource(id = R.drawable.ic_launcher_foreground),
			contentDescription = "App Logo",
			modifier = Modifier.size(100.dp)
		)

		Spacer(modifier = Modifier.height(32.dp))

		OutlinedTextField(
			value = email,
			onValueChange = { email = it },
			label = { Text(text = "Email") },
			singleLine = true,
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Email
			),
			keyboardActions = KeyboardActions(onDone = { /* Handle keyboard done action if needed */ }),
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = password,
			onValueChange = { password = it },
			label = { Text(text = "Password") },
			singleLine = true,
			visualTransformation = PasswordVisualTransformation(),
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Password
			),
			keyboardActions = KeyboardActions(onDone = { /* Handle keyboard done action if needed */ }),
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(32.dp))

		Button(
			onClick = {
				loginViewModel.login(context, email, password)
			}, modifier = Modifier.fillMaxWidth(), enabled = isLoginButtonEnabled
		) {
			Text(text = "Login")
		}

		Spacer(modifier = Modifier.height(16.dp))

		Text(text = "Forgot Password?",
			modifier = Modifier.clickable { /* Agregar lógica para restablecer la contraseña */ })

		Spacer(modifier = Modifier.height(16.dp))

		Text(text = "Register",
			modifier = Modifier.clickable { /* Agregar lógica para abrir la pantalla de registro */ })
	}
}

@Preview
@Composable
fun previewScreen() {
	LoginScreen(LoginViewModel())
}