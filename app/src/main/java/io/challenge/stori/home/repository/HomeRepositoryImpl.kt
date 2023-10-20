package io.challenge.stori.home.repository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.database
import io.challenge.stori.home.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeRepositoryImpl : HomeRepository {
	override suspend fun loadBankAccountData(userId: String): List<Transaction> {
		return withContext(Dispatchers.IO) {
			val database = Firebase.database
			val transactionsRef = database.getReference("$TRANSACTIONS_PATH/$userId")

			val transactions = mutableListOf<Transaction>()

			try {
				val dataSnapshot = transactionsRef.get().await()

				if (dataSnapshot.exists()) {
					for (childSnapshot in dataSnapshot.children) {
						val title = childSnapshot.child(TITLE_FIELD).getValue(String::class.java)
						val amount = childSnapshot.child(AMOUNT_FIELD).getValue(Double::class.java)
						val date = childSnapshot.child(DATE_FIELD).getValue(String::class.java)
						val transactionNumber =
							childSnapshot.child(TRANSACTION_NUMBER_FIELD)
								.getValue(Long::class.java)
						val paymentMethod =
							childSnapshot.child(PAYMENT_METHOD_FIELD).getValue(String::class.java)
						val cardNumber =
							childSnapshot.child(CARD_NUMBER_FIELD).getValue(String::class.java)
						val cardProvider =
							childSnapshot.child(CARD_PROVIDER_FIELD).getValue(String::class.java)

						transactions.add(
							Transaction(
								title,
								amount,
								date,
								transactionNumber,
								paymentMethod,
								cardNumber,
								cardProvider
							)
						)
					}
				}
			} catch (e: DatabaseException) {
				Log.e(
					"FirebaseDatabaseError",
					"Error de Firebase Database no identificado: ${e.message}"
				)

			} catch (e: Exception) {
				Log.e("GeneralError", "Error general: ${e.message}")
			}

			return@withContext transactions
		}
	}
}

private const val TRANSACTIONS_PATH = "transactions"
private const val TITLE_FIELD = "title"
private const val AMOUNT_FIELD = "amount"
private const val TRANSACTION_NUMBER_FIELD = "transactionNumber"
private const val CARD_PROVIDER_FIELD = "paymentMethod"
private const val CARD_NUMBER_FIELD = "cardNumber"
private const val PAYMENT_METHOD_FIELD = "cardProvider"
private const val DATE_FIELD = "date"