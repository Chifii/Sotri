package io.challenge.stori.home.view.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.challenge.stori.R
import io.challenge.stori.home.model.Transaction

@Composable
fun TransactionCard(
	transaction: Transaction
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp),
		shape = RoundedCornerShape(16.dp),
		elevation = 4.dp
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Row {
				Box(
					modifier = Modifier
						.size(48.dp)
						.clip(CircleShape)
						.background(Color.White)
						.border(1.dp, Color(0xFFE5E5E5), CircleShape)
						.padding(4.dp),
					contentAlignment = Alignment.Center
				) {
					Row {
						Image(
							painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu icono
							contentDescription = null,
							modifier = Modifier.size(32.dp)
						)
					}
				}
				Text(
					text = transaction.title,
					fontSize = 14.sp,
					modifier = Modifier
						.padding(start = 24.dp)
						.align(Alignment.CenterVertically)
				)
			}

			Column {
				Text(
					text = if (transaction.amount >= 0) "+$${transaction.amount}" else "-$${-transaction.amount}",
					fontSize = 18.sp,
					fontWeight = FontWeight.Bold,
					color = if (transaction.amount >= 0) Color(0xFF3F48CC) else Color(0xFFEF4F4F)
				)
			}
		}
	}
}

@Preview
@Composable
fun TransactionCardPreview() {
	TransactionCard(
		Transaction(
			"Envio dinero a Juan Perez",
			"Public Transport",
			-20.0
		)
	)
}

