package io.challenge.stori.login.repository

import android.content.Context
import io.challenge.stori.firebase.dataSource.LoginFirebaseDataSource

class LoginRepository(private val firebaseDataSource: LoginFirebaseDataSource) {
	fun signIn(context: Context, email: String, password: String) =
		firebaseDataSource.signIn(context, email, password)
}
