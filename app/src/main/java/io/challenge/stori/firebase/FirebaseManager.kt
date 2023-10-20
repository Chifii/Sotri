package io.challenge.stori.firebase

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

object FirebaseManager {

	private var authInstance: FirebaseAuth? = null
	private var databaseInstance: FirebaseDatabase? = null
	private var firestoreInstance: FirebaseFirestore? = null
	private var storageInstance: FirebaseStorage? = null
	private var firebaseAuth: FirebaseAuth? = null

	fun getAuthInstance(): FirebaseAuth {
		if (authInstance == null) {
			authInstance = FirebaseAuth.getInstance(FirebaseApp.getInstance())
			Log.d("FirebaseManager", "Firebase Auth inicializado")
		}
		return authInstance as FirebaseAuth
	}

	fun initFirebaseAuth() {
		firebaseAuth = Firebase.auth
	}

	fun getAuth(): FirebaseAuth? {
		return firebaseAuth
	}

	fun getDatabaseInstance(): FirebaseDatabase {
		if (databaseInstance == null) {
			databaseInstance = FirebaseDatabase.getInstance(FirebaseApp.getInstance())
		}
		return databaseInstance as FirebaseDatabase
	}

	fun getFirestoreInstance(): FirebaseFirestore {
		if (firestoreInstance == null) {
			firestoreInstance = FirebaseFirestore.getInstance(FirebaseApp.getInstance())
		}
		return firestoreInstance as FirebaseFirestore
	}

	fun getStorageInstance(): FirebaseStorage {
		if (storageInstance == null) {
			storageInstance = FirebaseStorage.getInstance(FirebaseApp.getInstance())
		}
		return storageInstance as FirebaseStorage
	}
}
