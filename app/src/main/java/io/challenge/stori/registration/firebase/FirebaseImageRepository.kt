package io.challenge.stori.registration.firebase

import android.net.Uri
import io.challenge.stori.registration.dataSource.FirebaseDataSource
import io.challenge.stori.registration.repository.ImageRepository
import io.challenge.stori.utils.Result

class FirebaseImageRepository(private val dataSource: FirebaseDataSource) : ImageRepository {
	override suspend fun uploadImage(imageUri: Uri): Result<String> {
		return dataSource.uploadImage(imageUri)
	}

	override suspend fun saveImageUrl(userId: String, imageUrl: String): Result<Unit> {
		return dataSource.saveImageUrl(userId, imageUrl)
	}
}