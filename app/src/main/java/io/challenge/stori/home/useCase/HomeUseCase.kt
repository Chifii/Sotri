package io.challenge.stori.home.useCase

import io.challenge.stori.home.model.Transaction
import io.challenge.stori.home.repository.HomeRepository

class HomeUseCase(private val repository: HomeRepository) {
	suspend fun loadBankAccountData(userId: String): List<Transaction> {
		return repository.loadBankAccountData(userId)
	}
}
