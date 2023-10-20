package io.challenge.stori.home.repository

import io.challenge.stori.home.model.Transaction

interface HomeRepository {
	suspend fun loadBankAccountData(userId: String): List<Transaction>
}

