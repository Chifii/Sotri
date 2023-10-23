package io.challenge.stori.registration.useCase

import io.challenge.stori.registration.repository.UserRepository
import io.challenge.stori.utils.Result

class UserRegisterUseCase(private val repository: UserRepository) {
	suspend fun registerUser(
		email: String,
		password: String,
		firstName: String,
		lastName: String
	): Result<String> {
		return repository.registerUser(email, password, firstName, lastName)
	}
}
