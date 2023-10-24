package io.challenge.stori.registration.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.challenge.stori.R
import io.challenge.stori.home.view.ui.COLOR_RED
import io.challenge.stori.login.view.LoginActivity
import io.challenge.stori.registration.firebase.FirebaseUserRepository
import io.challenge.stori.registration.useCase.UserRegisterUseCase
import io.challenge.stori.registration.viewModel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {

	private val viewModel = RegistrationViewModel(UserRegisterUseCase(FirebaseUserRepository()))

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val callback = object : OnBackPressedCallback(
			true
		) {
			override fun handleOnBackPressed() {
				finish()
				val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
				intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
				startActivity(intent)
			}
		}
		this.onBackPressedDispatcher.addCallback(
			this, callback
		)

		setContent {
			RegistrationScreen(
				viewModel
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen(
	viewModel: RegistrationViewModel
) {
	val keyboardController = LocalSoftwareKeyboardController.current

	val emailFocusRequester = remember { FocusRequester() }
	val passwordFocusRequester = remember { FocusRequester() }
	val nameFocusRequester = remember { FocusRequester() }
	val lastNameFocusRequester = remember { FocusRequester() }

	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var firstName by remember { mutableStateOf("") }
	var lastName by remember { mutableStateOf("") }
	var isPasswordVisible by remember { mutableStateOf(false) }

	val context = LocalContext.current

	val userId by viewModel.userId.observeAsState()
	val error by viewModel.error.observeAsState()

	var isPasswordError by remember { mutableStateOf(false) }

	if (userId != null) {
		val intent = Intent(context, CameraActivity::class.java)
		intent.putExtra("userId", userId)
		context.startActivity(intent)
	}

	if (error != null) {
		Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
	}

	val isRegisterButtonEnabled = viewModel.validateAllFields(email, password, firstName, lastName)

	Box(
		modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = stringResource(id = R.string.register_title),
				fontSize = 32.sp,
				fontWeight = FontWeight.Bold,
				color = Color(COLOR_RED),
				modifier = Modifier.align(Alignment.Start)
			)

			OutlinedTextField(
				value = email,
				onValueChange = { email = it },
				label = { Text(stringResource(id = R.string.register_email)) },
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Email, imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(onDone = { passwordFocusRequester.requestFocus() }),
				singleLine = true,
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp)
					.focusRequester(emailFocusRequester)
			)

			OutlinedTextField(
				value = password,
				onValueChange = { newPassword ->
					password = newPassword
					isPasswordError = false
				},
				label = { Text(stringResource(id = R.string.register_password)) },
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = if (isPasswordVisible) KeyboardType.Text else KeyboardType.Password,
					imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(onDone = {
					if (viewModel.validatePassword(password)) {
						nameFocusRequester.requestFocus()
					} else {
						isPasswordError = true
						passwordFocusRequester.requestFocus()
						Toast.makeText(
							context, PASSWORD_ERROR, Toast.LENGTH_SHORT
						).show()
					}
				}),
				singleLine = true,
				visualTransformation = if (!isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
				trailingIcon = {
					val icon = if (!isPasswordVisible) {
						Icons.Default.VisibilityOff
					} else {
						Icons.Default.Visibility
					}
					IconButton(onClick = {
						isPasswordVisible = !isPasswordVisible
					}) {
						Icon(
							imageVector = icon, contentDescription = null
						)
					}
				},
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp)
					.focusRequester(passwordFocusRequester),
				colors = TextFieldDefaults.outlinedTextFieldColors(
					focusedBorderColor = if (isPasswordError) Color.Red else MaterialTheme.colorScheme.primary,
					unfocusedBorderColor = if (isPasswordError) Color.Red else MaterialTheme.colorScheme.primary
				)
			)

			OutlinedTextField(
				value = firstName,
				onValueChange = { firstName = it },
				label = { Text(stringResource(id = R.string.register_first_name)) },
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(onDone = { lastNameFocusRequester.requestFocus() }),
				singleLine = true,
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp)
					.focusRequester(nameFocusRequester)
			)

			OutlinedTextField(
				value = lastName,
				onValueChange = { lastName = it },
				label = { Text(stringResource(id = R.string.register_last_name)) },
				keyboardOptions = KeyboardOptions.Default.copy(
					keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
				singleLine = true,
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp)
					.focusRequester(lastNameFocusRequester)
			)

			Button(
				onClick = {
					viewModel.register(
						email = email,
						password = password,
						firstName = firstName,
						lastName = lastName
					)

				},
				enabled = isRegisterButtonEnabled,
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 16.dp)
			) {
				Text(stringResource(id = R.string.title_activity_register))
			}
		}
	}
}

private const val PASSWORD_ERROR = "The password must have at least 6 characters"

@Preview
@Composable
fun PreviewRegistration() {
	RegistrationScreen(RegistrationViewModel(UserRegisterUseCase(FirebaseUserRepository())))
}
