package io.challenge.stori.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.challenge.stori.home.model.BankAccountData
import io.challenge.stori.home.model.Transaction

class HomeViewModel : ViewModel() {

	private var bankAccountDataMLD: MutableLiveData<BankAccountData> = MutableLiveData()
	val bankAccountData get() = bankAccountDataMLD as LiveData<BankAccountData>

	fun loadBankAccountData() {
		val sampleData = BankAccountData(
			accountNumber = "1234 5678 9012 3456",
			balance = 2500.0,
			transactions = listOf(
				Transaction("Compra en línea", "15/10/2023", -200.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Depósito", "14/10/2023", 1000.0),
				Transaction("Restaurante", "13/10/2023", -50.0)
			)
		)
		bankAccountDataMLD.value = sampleData
	}
}

