package io.challenge.stori.home.view.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.challenge.stori.R

@Composable
fun BalanceCard(totalBalance: String, todayBalance: String) {
	var isBalanceVisible by remember { mutableStateOf(false) }

	Card(
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxWidth()
			.padding(16.dp),
		elevation = 16.dp
	) {
		Column(
			modifier = Modifier.padding(16.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = stringResource(id = R.string.balance_card_total),
					fontSize = 16.sp,
					color = Color.Black
				)

				Icon(
					painter = if (isBalanceVisible) {
						painterResource(id = R.drawable.visibility_fill)
					} else {
						painterResource(id = R.drawable.visibility_off_fill)
					},
					contentDescription = stringResource(id = R.string.balance_card_content_description),
					modifier = Modifier
						.padding(start = 8.dp, top = 4.dp)
						.align(alignment = Alignment.CenterVertically)
						.clickable {
							isBalanceVisible = !isBalanceVisible
						},
				)
			}

			Spacer(modifier = Modifier.height(2.dp))

			Text(
				text = if (isBalanceVisible) "$ $totalBalance" else stringResource(id = R.string.balance_card_hide_numbers),
				fontSize = 36.sp,
				fontWeight = FontWeight.Bold,
				color = Color(COLOR_BLUE)
			)

			Spacer(
				modifier = Modifier
					.height(8.dp)
					.padding(start = 4.dp)
			)
			Text(
				text = if (isBalanceVisible) "$ $todayBalance" else stringResource(id = R.string.balance_card_hide_numbers),
				fontSize = 16.sp,
				fontWeight = FontWeight.Bold,
				color = Color(COLOR_RED)
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

