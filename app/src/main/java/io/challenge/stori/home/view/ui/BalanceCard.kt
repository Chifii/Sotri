package io.challenge.stori.home.view.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BalanceCard(totalBalance: String, todayBalance: String) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxWidth()
			.padding(16.dp),
		elevation = 8.dp
	) {
		Column(
			modifier = Modifier.padding(16.dp)
		) {
			Text(
				text = "Total:",
				fontSize = 20.sp,
				color = Color.Black
			)
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				text = "$$totalBalance",
				fontSize = 36.sp,
				fontWeight = FontWeight.Bold,
				color = Color(0xFF3F48CC)
			)
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				text = "$$todayBalance",
				fontSize = 14.sp,
				fontWeight = FontWeight.Bold,
				color = Color(0xFFEF4F4F)
			)
		}
	}
}

@Preview
@Composable
fun BalanceCardPreview() {
	BalanceCard(
		totalBalance = "2500.0",
		todayBalance = "-50.0"
	)
}

