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
						.border(1.dp, Color(COLOR_GRAY), CircleShape)
						.padding(4.dp),
					contentAlignment = Alignment.Center
				) {
					transaction.title?.let { _ ->
						Image(
							painter = if (transaction.amount!! >= 0) {
								painterResource(id = R.drawable.trending_up)
							} else {
								painterResource(id = R.drawable.trending_down)
							},
							contentDescription = null,
							modifier = Modifier.size(32.dp)
						)
					}
				}
				transaction.title?.let { title ->
					Text(
						text = title,
						fontSize = 16.sp,
						modifier = Modifier
							.padding(start = 24.dp)
							.align(Alignment.CenterVertically)
					)
				}
			}

			Column {
				transaction.amount?.let { amount ->
					val formattedAmount =
						if (amount >= 0) "+${"%.2f".format(amount)}" else "-${"%.2f".format(-amount)}"

					Text(
						text = formattedAmount,
						fontSize = 18.sp,
						fontWeight = FontWeight.Bold,
						color = if (amount >= 0) Color(COLOR_BLUE) else Color(COLOR_RED)
					)
				}
			}
		}
	}
}

const val COLOR_RED = 0xFFEF4F4F
const val COLOR_BLUE = 0xFF3F48CC
const val COLOR_GRAY = 0xFFE5E5E5