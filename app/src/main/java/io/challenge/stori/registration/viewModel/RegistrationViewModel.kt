package io.challenge.stori.registration.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.challenge.stori.registration.useCase.UserRegisterUseCase
import io.challenge.stori.utils.Result
import kotlinx.coroutines.launch

class RegistrationViewModel(private val registerUseCase: UserRegisterUseCase) : ViewModel() {

	private var errorMLD: MutableLiveData<String> = MutableLiveData()
	val error get() = errorMLD as LiveData<String>

	private var userIdMLD: MutableLiveData<String> = MutableLiveData()
	val userId get() = userIdMLD as LiveData<String>

	fun register(email: String, password: String, firstName: String, lastName: String) {
		viewModelScope.launch {
			when (val result = registerUseCase.registerUser(email, password, firstName, lastName)) {
				is Result.Success -> {
					userIdMLD.value = result.data
				}

				is Result.Error -> {
					errorMLD.value = result.exception.message
				}
			}
		}
	}

	private fun validateField(value: String): Boolean {
		return value.isNotEmpty() && value.length >= FIELD_LENGTH
	}

	fun validatePassword(value: String): Boolean {
		return value.isNotEmpty() && value.length >= PASSWORD_LENGTH
	}

	private fun validateEmail(email: String): Boolean {
		val emailPattern = EMAIL_PATTERN
		return email.matches(emailPattern.toRegex())
	}

	fun validateAllFields(
		email: String, password: String, firstName: String, lastName: String
	): Boolean {
		return validateEmail(email) && validatePassword(password) && validateField(firstName) && validateField(
			lastName
		)
	}
}

private const val EMAIL_PATTERN = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
private const val PASSWORD_LENGTH = 6
private const val FIELD_LENGTH = 3