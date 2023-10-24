package io.challenge.stori.registration.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.challenge.stori.registration.repository.UserRepository
import io.challenge.stori.utils.Result
import kotlinx.coroutines.tasks.await

class FirebaseUserRepository : UserRepository {
	private var result: Result<String> = Result.Success("")

	override suspend fun registerUser(
		email: String, password: String, firstName: String, lastName: String
	): Result<String> {
		return try {
			FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						val user = FirebaseAuth.getInstance().currentUser

						val userId = user?.uid

						Log.d("FirebaseUserRepository", "userId: $userId")

						val userData = mapOf(
							"firstName" to firstName, "lastName" to lastName
						)

						Log.d("FirebaseUserRepository", "userData: $userData")

						userId?.let {
							Firebase.database.reference.child("users").child(userId)
								.setValue(userData)
						}

						result = Result.Success(userId.toString())
					} else {
						Log.e("FirebaseUserRepository", "Error: ${task.exception?.message}")

						result = Result.Error(Exception(task.exception?.message))
					}
				}.await()
			result
		} catch (e: Exception) {
			Log.e("FirebaseUserRepository", "Error: ${e.message}")
			Result.Error(e)
		}
	}
}
