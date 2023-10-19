package io.challenge.stori.firebase.repository

import android.content.Context
import io.challenge.stori.firebase.FirebaseInitializer

class FirebaseRepository(private val firebaseInitializer: FirebaseInitializer) {
	fun initializeFirebase(context: Context) {
		firebaseInitializer.initialize(context)
	}
}
