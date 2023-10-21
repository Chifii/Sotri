package io.challenge.stori.registration.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
	/*
	private var bankAccountDataMLD: MutableLiveData<List<Transaction>> = MutableLiveData()
	val bankAccountData get() = bankAccountDataMLD as LiveData<List<Transaction>>
	 */
	private var emailMLD: MutableLiveData<String> = MutableLiveData()
	val email get() = emailMLD as LiveData<String>

	private var passwordMLD: MutableLiveData<String> = MutableLiveData()
	val password get() = passwordMLD as LiveData<String>

	private var firstNameMLD: MutableLiveData<String> = MutableLiveData()
	val firstName get() = firstNameMLD as LiveData<String>

	private var lastNameMLD: MutableLiveData<String> = MutableLiveData()
	val lastName get() = lastNameMLD as LiveData<Boolean>

	fun register(email: String, password: String, firstName: String, lastName: String) {
		emailMLD.value = email
		passwordMLD.value = password
		firstNameMLD.value = firstName
		lastNameMLD.value = lastName
	}

	private fun validateField(value: String): Boolean {
		return value.isNotEmpty() && value.length >= 3
	}

	private fun validatePassword(value: String): Boolean {
		return value.isNotEmpty() && value.length >= 6
	}

	private fun validateEmail(email: String): Boolean {
		val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
		return email.matches(emailPattern.toRegex())
	}

	fun validateAllFields(
		email: String,
		password: String,
		firstName: String,
		lastName: String
	): Boolean {
		return validateEmail(email)
				&& validatePassword(password)
				&& validateField(firstName)
				&& validateField(lastName)
	}


}
