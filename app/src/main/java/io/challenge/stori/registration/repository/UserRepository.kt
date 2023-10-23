package io.challenge.stori.registration.repository

import io.challenge.stori.utils.Result

interface UserRepository {
	suspend fun registerUser(
		email: String,
		password: String,
		firstName: String,
		lastName: String
	): Result<String>
}
