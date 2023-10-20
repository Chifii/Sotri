package io.challenge.stori.home.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerCompose() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.shimmer()
			.padding(16.dp)
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.size(120.dp)
				.background(Color.LightGray)
		)

		Spacer(modifier = Modifier.height(24.dp))

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.size(24.dp)
				.background(Color.LightGray)
		)

		Spacer(modifier = Modifier.height(24.dp))

		repeat(4) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.size(80.dp)
					.background(Color.LightGray)
			)

			Spacer(modifier = Modifier.height(12.dp))
		}
	}
}

@Preview
@Composable
fun ShimmerPreview() {
	ShimmerCompose()
}