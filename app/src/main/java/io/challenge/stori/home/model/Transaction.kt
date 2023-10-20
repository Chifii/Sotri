package io.challenge.stori.home.model

data class Transaction(
	val title: String?,
	val amount: Double?,
	val date: String?,
	val transactionNumber: Long?,
	val paymentMethod: String?,
	val cardNumber: String?,
	val cardProvider: String?
)
