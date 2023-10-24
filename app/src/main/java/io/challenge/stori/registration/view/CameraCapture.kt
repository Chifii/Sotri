package io.challenge.stori.registration.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import io.challenge.stori.BuildConfig
import io.challenge.stori.R
import io.challenge.stori.home.view.HomeActivity
import io.challenge.stori.registration.dataSource.dataSourceImpl.FirebaseDataSourceImpl
import io.challenge.stori.registration.firebase.FirebaseImageRepository
import io.challenge.stori.registration.useCase.SaveImageUrlUseCase
import io.challenge.stori.registration.useCase.UploadImageUseCase
import io.challenge.stori.registration.viewModel.CameraCaptureViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

class CameraActivity : ComponentActivity() {

	private val viewModel = CameraCaptureViewModel(
		uploadImageUseCase = UploadImageUseCase(
			FirebaseImageRepository(
				FirebaseDataSourceImpl()
			)
		), saveImageUrlUseCase = SaveImageUrlUseCase(
			FirebaseImageRepository(
				FirebaseDataSourceImpl()
			)
		)
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.setUser(intent.getStringExtra(USER_ID) ?: "0")
		setContent {
			setContent {
				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
				) {
					CameraScreen(
						viewModel
					)
				}
			}
		}
	}
}


@Composable
fun CameraScreen(
	viewModel: CameraCaptureViewModel
) {
	val goToHome by viewModel.goToHome.observeAsState()
	val error by viewModel.error.observeAsState()

	val context = LocalContext.current

	if (goToHome == true) {
		Toast.makeText(context, REGISTER_SUCCESSFULLY, Toast.LENGTH_SHORT).show()

		val intent = Intent(context, HomeActivity::class.java)
		context.startActivity(intent)
	}

	if (error?.isNotEmpty() == true) {
		Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
	}

	val file = context.createImageFile()
	val uri = FileProvider.getUriForFile(
		Objects.requireNonNull(context), BuildConfig.APPLICATION_ID + ".provider", file
	)

	var capturedImageUri by remember {
		mutableStateOf<Uri>(Uri.EMPTY)
	}

	val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
		capturedImageUri = uri
	}

	val permissionLauncher = rememberLauncherForActivityResult(
		ActivityResultContracts.RequestPermission()
	) {
		if (it) {
			Toast.makeText(context, PERMISSION_GRANTED, Toast.LENGTH_SHORT).show()
			cameraLauncher.launch(uri)
		} else {
			Toast.makeText(context, PERMISSION_DENIED, Toast.LENGTH_SHORT).show()
		}
	}

	val permissionCheckResult =
		ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

	Box(
		modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = stringResource(id = R.string.register_photo_title),
				fontSize = 32.sp,
				fontWeight = FontWeight.Bold,
				color = Color.Blue,
				modifier = Modifier
					.align(Alignment.Start)
					.padding(top = 16.dp, start = 2.dp, end = 16.dp)
			)

			Text(
				text = stringResource(id = R.string.register_photo_details),
				modifier = Modifier
					.align(Alignment.Start)
					.padding(4.dp, bottom = 8.dp),
				color = Color.Gray,
			)

			Box(
				contentAlignment = Alignment.Center
			) {
				Box(
					modifier = Modifier
						.size(400.dp)
						.border(2.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
				) {
					if (capturedImageUri.path?.isNotEmpty() != true) {
						Icon(imageVector = Icons.Default.CameraAlt,
							contentDescription = stringResource(id = R.string.register_take_photo),
							modifier = Modifier
								.size(100.dp)
								.align(Alignment.Center)
								.clickable {
									if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
										cameraLauncher.launch(uri)
									} else {
										permissionLauncher.launch(Manifest.permission.CAMERA)
									}
								}
								.padding(bottom = 16.dp))
					} else {
						Image(
							painter = rememberAsyncImagePainter(capturedImageUri),
							contentDescription = null,
							modifier = Modifier
								.fillMaxSize()
								.padding(bottom = 16.dp, top = 16.dp)
						)
					}
				}
			}

			if (capturedImageUri.path?.isNotEmpty() == true) {
				Button(onClick = {
					viewModel.uploadImageAndSaveUrl(capturedImageUri)
				}) {
					Text(text = stringResource(id = R.string.register_continue_button))
				}
			} else {
				Button(onClick = {
					if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
						cameraLauncher.launch(uri)
					} else {
						permissionLauncher.launch(Manifest.permission.CAMERA)
					}
				}) {
					Text(text = stringResource(id = R.string.register_take_photo))
				}
			}
		}
	}


}

fun Context.createImageFile(): File {
	val timeStamp = SimpleDateFormat(TIME_STAMP).format(Date())
	val imageFileName = "JPEG_" + timeStamp + "_"
	return File.createTempFile(
		imageFileName, ".jpg", externalCacheDir
	)
}

private const val REGISTER_SUCCESSFULLY = "Your account has been created successfully."
private const val PERMISSION_GRANTED = "Permission Granted"
private const val PERMISSION_DENIED = "Permission Denied"
private const val USER_ID = "userId"
private const val TIME_STAMP = "yyyyMMdd_HHmmss"