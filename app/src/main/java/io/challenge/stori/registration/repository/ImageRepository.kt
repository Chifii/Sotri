package io.challenge.stori.registration.repository

import android.net.Uri
import io.challenge.stori.utils.Result

interface ImageRepository {
	suspend fun uploadImage(imageUri: Uri): Result<String>
	suspend fun saveImageUrl(userId: String, imageUrl: String): Result<Unit>
}
