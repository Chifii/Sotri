package io.challenge.stori.firebase

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions


class FirebaseInitializerImpl : FirebaseInitializer {
	private var initialized = false

	override fun initialize(context: Context) {
		if (!initialized) {
			val options = FirebaseOptions.Builder()
				.setApplicationId("1:402321813734:android:2032e6321b6d7caa690b27")
				.setApiKey("AIzaSyBK0GkzmO_Pi2_NiHmAHb9HE5gjmstqCUI")
				.setProjectId("1:402321813734:android:2032e6321b6d7caa690b27")
				.build()

			FirebaseApp.initializeApp(context, options)
			initialized = true
		}
	}
}

