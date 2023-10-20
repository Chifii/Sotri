package io.challenge.stori.home.model

data class BankAccountData(
	val balance: Double,
	val transactions: List<Transaction>
)
