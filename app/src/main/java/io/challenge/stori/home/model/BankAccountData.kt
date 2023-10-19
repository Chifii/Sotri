package io.challenge.stori.home.model

data class BankAccountData(
	val accountNumber: String,
	val balance: Double,
	val transactions: List<Transaction>
)
