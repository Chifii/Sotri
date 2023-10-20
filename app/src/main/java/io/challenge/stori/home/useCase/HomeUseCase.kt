package io.challenge.stori.home.useCase

import io.challenge.stori.home.model.Transaction
import io.challenge.stori.home.repository.HomeRepositoryImpl

class HomeUseCase(private val repository: HomeRepositoryImpl) {
	suspend fun loadBankAccountData(userId: String): List<Transaction> {
		return repository.loadBankAccountData(userId)
	}
}
