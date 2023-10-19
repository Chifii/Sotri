package io.challenge.stori.login.useCase

import android.content.Context
import io.challenge.stori.login.repository.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {
	fun signIn(context: Context, email: String, password: String) =
		loginRepository.signIn(context, email, password)
}