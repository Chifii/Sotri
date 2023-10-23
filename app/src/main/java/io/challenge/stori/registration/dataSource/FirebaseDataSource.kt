package io.challenge.stori.registration.dataSource

import android.net.Uri
import io.challenge.stori.utils.Result

interface FirebaseDataSource {
	suspend fun uploadImage(imageUri: Uri): Result<String>
	suspend fun saveImageUrl(userId: String, imageUrl: String): Result<Unit>
}

