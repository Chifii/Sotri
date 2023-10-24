package io.challenge.stori.registration.viewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.challenge.stori.registration.useCase.SaveImageUrlUseCase
import io.challenge.stori.registration.useCase.UploadImageUseCase
import io.challenge.stori.utils.Result
import kotlinx.coroutines.launch

class CameraCaptureViewModel(
	private val uploadImageUseCase: UploadImageUseCase,
	private val saveImageUrlUseCase: SaveImageUrlUseCase
) : ViewModel() {
	private var userId: String = "0"

	private var goToHomeMLD: MutableLiveData<Boolean> = MutableLiveData()
	val goToHome get() = goToHomeMLD as LiveData<Boolean>

	private var errorMLD: MutableLiveData<String> = MutableLiveData()
	val error get() = errorMLD as LiveData<String>

	fun uploadImageAndSaveUrl(imageUri: Uri) {
		viewModelScope.launch {
			val uploadResult = uploadImageUseCase(imageUri)
			if (uploadResult is Result.Success) {
				val imageUrl = uploadResult.data
				when (val saveResult = saveImageUrlUseCase(userId, imageUrl)) {
					is Result.Success -> {
						goToHomeMLD.value = true
					}

					is Result.Error -> {
						errorMLD.value = saveResult.exception.message
					}
				}
			} else if (uploadResult is Result.Error) {
				errorMLD.value = uploadResult.exception.message
			}
		}
	}

	fun setUser(userId: String) {
		this.userId = userId
	}
}
