package io.challenge.stori.firebase.useCase

import android.content.Context
import io.challenge.stori.firebase.repository.FirebaseRepository

class InitializeFirebaseUseCase(private val firebaseRepository: FirebaseRepository) {
	operator fun invoke(context: Context) {
		firebaseRepository.initializeFirebase(context)
	}
}
