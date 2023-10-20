package io.challenge.stori.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.challenge.stori.home.model.Transaction
import io.challenge.stori.home.useCase.HomeUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
	private val homeUseCase: HomeUseCase
) : ViewModel() {


	private var bankAccountDataMLD: MutableLiveData<List<Transaction>> = MutableLiveData()
	val bankAccountData get() = bankAccountDataMLD as LiveData<List<Transaction>>

	private var totalBalanceMLD: MutableLiveData<String> = MutableLiveData()
	val totalBalance get() = totalBalanceMLD as LiveData<String>

	private var negativeBalanceMLD: MutableLiveData<String> = MutableLiveData()
	val negativeBalance get() = negativeBalanceMLD as LiveData<String>

	fun loadBankAccountData(userId: String) {
		viewModelScope.launch {
			val data = homeUseCase.loadBankAccountData(userId)
			delay(2500)
			bankAccountDataMLD.value = data
			calculateBalances(data)
		}
	}

	private fun calculateBalances(transactions: List<Transaction>) {
		var totalBalance = 0.0
		var negativeBalance = 0.0

		for (transaction in transactions) {
			transaction.amount?.let { amount ->
				totalBalance += amount
				if (amount < 0) {
					negativeBalance += amount
				}
			}
		}

		totalBalanceMLD.value = totalBalance.toString()
		negativeBalanceMLD.value = negativeBalance.toString()
	}
}

