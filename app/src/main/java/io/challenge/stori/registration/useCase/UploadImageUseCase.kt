package io.challenge.stori.registration.useCase

import android.net.Uri
import io.challenge.stori.registration.repository.ImageRepository
import io.challenge.stori.utils.Result


class UploadImageUseCase(private val repository: ImageRepository) {
	suspend operator fun invoke(imageUri: Uri): Result<String> {
		return repository.uploadImage(imageUri)
	}
}