package io.challenge.stori.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

	private val isAuthenticatedMLD = MutableLiveData(false)
	val isAuthenticated: LiveData<Boolean> = isAuthenticatedMLD

	private val failLoginMLD = MutableLiveData(false)
	val failLogin: LiveData<Boolean> = failLoginMLD

	private val navigateToHomeMLD = MutableLiveData(false)
	val navigateToHome: LiveData<Boolean> = navigateToHomeMLD

	private val invalidEmailMLD = MutableLiveData(false)
	val invalidEmail: LiveData<Boolean> = invalidEmailMLD

	private var userId: String? = ""

	fun login(email: String, password: String) {
		if (!isEmailValid(email)) {
			invalidEmailMLD.value = true
			return
		} else {
			viewModelScope.launch {
				var auth = Firebase.auth
				auth.signInWithEmailAndPassword(email, password)
					.addOnCompleteListener {
						if (it.isSuccessful) {
							isAuthenticatedMLD.value = true
							navigateToHomeMLD.value = true
						} else {
							failLoginMLD.value = true
						}
					}
				userId = auth.currentUser?.providerId
			}
		}
	}

	private fun isEmailValid(email: String): Boolean {
		val emailPattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
		return email.matches(emailPattern.toRegex())
	}

	fun restartFailLogin() {
		failLoginMLD.value = false
	}

	fun restartEmail() {
		invalidEmailMLD.value = false
	}

	fun getUserId(): String? {
		return userId
	}

}
