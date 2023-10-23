package io.challenge.stori.registration.useCase

import io.challenge.stori.registration.repository.ImageRepository
import io.challenge.stori.utils.Result

class SaveImageUrlUseCase(private val repository: ImageRepository) {
	suspend operator fun invoke(userId: String, imageUrl: String): Result<Unit> {
		return repository.saveImageUrl(userId, imageUrl)
	}
}