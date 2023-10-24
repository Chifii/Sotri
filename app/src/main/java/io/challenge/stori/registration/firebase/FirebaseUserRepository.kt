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

						Log.d(TAG, "userId: $userId")

						val userData = mapOf(
							FIRST_NAME_FIELD to firstName, LAST_NAME_FIELD to lastName
						)

						Log.d(TAG, "userData: $userData")

						userId?.let {
							Firebase.database.reference.child(USERS_PATH).child(userId)
								.setValue(userData)
						}

						result = Result.Success(userId.toString())
					} else {
						Log.e(TAG, "Error: ${task.exception?.message}")

						result = Result.Error(Exception(task.exception?.message))
					}
				}.await()
			result
		} catch (e: Exception) {
			Log.e(TAG, "Error: ${e.message}")
			Result.Error(e)
		}
	}
}

private const val TAG = "FirebaseUserRepository"
private const val USERS_PATH = "users"
private const val FIRST_NAME_FIELD = "firstName"
private const val LAST_NAME_FIELD = "lastName"


