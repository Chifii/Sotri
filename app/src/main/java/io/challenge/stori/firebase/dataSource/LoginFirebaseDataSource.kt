package io.challenge.stori.firebase.dataSource

import android.content.Context
import io.challenge.stori.firebase.FirebaseManager

class LoginFirebaseDataSource(private val firebaseManager: FirebaseManager) {

	fun signIn(context: Context, email: String, password: String) =
		firebaseManager.getAuthInstance(context).signInWithEmailAndPassword(email, password)
}

