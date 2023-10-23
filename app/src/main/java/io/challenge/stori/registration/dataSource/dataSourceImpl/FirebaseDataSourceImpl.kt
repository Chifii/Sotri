package io.challenge.stori.registration.dataSource.dataSourceImpl

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.challenge.stori.registration.dataSource.FirebaseDataSource
import io.challenge.stori.utils.Result
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseDataSourceImpl : FirebaseDataSource {
	override suspend fun uploadImage(imageUri: Uri): Result<String> {
		return try {
			val storageReference = FirebaseStorage.getInstance().reference
			val imageRef = storageReference.child("images/${UUID.randomUUID()}.jpg")

			val uploadTask = imageRef.putFile(imageUri).await()

			if (uploadTask.task.isSuccessful) {
				val imageUrl = imageRef.downloadUrl.await().toString()
				Result.Success(imageUrl)
			} else {
				Result.Error(Exception("Error uploading image"))
			}
		} catch (e: Exception) {
			Result.Error(e)
		}
	}

	override suspend fun saveImageUrl(userId: String, imageUrl: String): Result<Unit> {
		return try {
			val databaseReference =
				FirebaseDatabase.getInstance().getReference("users/$userId/images")
			val imageNode = databaseReference.push()
			imageNode.setValue(imageUrl).await()
			Result.Success(Unit)
		} catch (e: Exception) {
			Result.Error(e)
		}
	}

}

