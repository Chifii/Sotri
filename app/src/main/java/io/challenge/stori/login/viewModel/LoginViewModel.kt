package io.challenge.stori.login.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.challenge.stori.firebase.FirebaseManager
import io.challenge.stori.firebase.dataSource.LoginFirebaseDataSource
import io.challenge.stori.login.repository.LoginRepository
import io.challenge.stori.login.useCase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

	private val loginUseCase: LoginUseCase =
		LoginUseCase(LoginRepository(LoginFirebaseDataSource(FirebaseManager)))

	private val isAuthenticatedMLD = MutableLiveData(false)
	val isAuthenticated: LiveData<Boolean> = isAuthenticatedMLD


	fun login(context: Context, email: String, password: String) {
		viewModelScope.launch {
			try {
				loginUseCase.signIn(context, email, password)

				isAuthenticatedMLD.value = true
			} catch (e: Exception) {
				isAuthenticatedMLD.value = false
			}
		}
	}

}
