package io.challenge.stori.movements.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.challenge.stori.R
import io.challenge.stori.home.model.Transaction
import io.challenge.stori.home.view.ui.COLOR_BLUE
import io.challenge.stori.home.view.ui.COLOR_RED

@Composable
fun TransactionDetailScreen(transaction: Transaction?) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(26.dp), elevation = 32.dp,
		shape = RoundedCornerShape(20.dp)
	) {
		Column(
			modifier = Modifier.padding(16.dp)
		) {
			Image(
				painter = painterResource(id = R.drawable.task_alt_fill),
				contentDescription = null,
				modifier = Modifier
					.size(120.dp)
					.clip(shape = RoundedCornerShape(16.dp))
					.background(Color.White)
					.align(Alignment.CenterHorizontally)
			)

			Spacer(modifier = Modifier.height(8.dp))

			Text(
				text = stringResource(id = R.string.transactions_card_title),
				fontSize = 36.sp,
				fontWeight = FontWeight.Bold,
				modifier = Modifier.align(Alignment.CenterHorizontally)
			)

			Spacer(modifier = Modifier.height(8.dp))

			Text(
				text = stringResource(id = R.string.transactions_card_description),
				fontSize = 12.sp,
				fontWeight = FontWeight.Light,
				color = Color(Color.Gray.value),
				modifier = Modifier.align(Alignment.CenterHorizontally)
			)

			Spacer(modifier = Modifier.height(16.dp))

			Text(
				text = if (transaction?.amount != null) "${transaction.amount}" else stringResource(
					id = R.string.transactions_card_n_a
				),
				fontSize = 30.sp,
				fontWeight = FontWeight.Bold,
				color = if (transaction?.amount!! >= 0) Color(COLOR_BLUE) else Color(COLOR_RED),
				modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.padding(end = 8.dp),
			)

			Spacer(modifier = Modifier.height(16.dp))

			DashedDivider(
				color = Color.LightGray,
				thickness = 1.dp,
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp)
			)


			Spacer(modifier = Modifier.height(16.dp))

			Text(
				text = stringResource(id = R.string.transactions_card_details_title),
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold
			)

			Spacer(modifier = Modifier.height(16.dp))

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = stringResource(id = R.string.transactions_card_details_number),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold
				)


				Text(
					text = if (transaction?.transactionNumber != null) "${transaction.transactionNumber}" else stringResource(
						id = R.string.transactions_card_n_a
					),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold,
					color = Color(Color.Gray.value)
				)
			}

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = stringResource(id = R.string.transactions_card_details_amount),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold
				)


				Text(
					text = if (transaction?.amount != null) "$ ${transaction.amount}" else stringResource(
						id = R.string.transactions_card_n_a
					),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold,
					color = Color(Color.Gray.value)
				)
			}

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = stringResource(id = R.string.transactions_card_details_date),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold
				)


				Text(
					text = if (transaction?.date != null) "${transaction.date}" else stringResource(
						id = R.string.transactions_card_n_a
					),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold,
					color = Color(Color.Gray.value)
				)
			}

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = stringResource(id = R.string.transactions_card_details_payment_method),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold
				)


				Text(
					text = if (transaction?.paymentMethod != null) "${transaction.paymentMethod}" else stringResource(
						id = R.string.transactions_card_n_a
					),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold,
					color = Color(Color.Gray.value)
				)
			}

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = stringResource(id = R.string.transactions_card_details_card_provider),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold
				)


				Text(
					text = if (transaction?.cardProvider != null) "${transaction.cardProvider}" else stringResource(
						id = R.string.transactions_card_n_a
					),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold,
					color = Color(Color.Gray.value)
				)
			}

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					text = stringResource(id = R.string.transactions_card_details_number),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold
				)


				Text(
					text = if (transaction?.cardNumber != null) "${transaction.cardNumber}" else stringResource(
						id = R.string.transactions_card_n_a
					),
					fontSize = 12.sp,
					fontWeight = FontWeight.Bold,
					color = Color(Color.Gray.value)
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			DashedDivider(
				color = Color.LightGray,
				thickness = 1.dp,
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp)
			)

			Spacer(modifier = Modifier.height(16.dp))

			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(2.dp),
				horizontalArrangement = Arrangement.Center
			) {
				Image(
					painter = painterResource(id = R.drawable.storilogo),
					contentDescription = null,
					modifier = Modifier
						.size(24.dp)
						.clip(shape = RoundedCornerShape(16.dp))
						.background(Color.LightGray)
						.border(1.dp, Color.Gray, shape = RoundedCornerShape(16.dp)),
				)

				Text(
					text = stringResource(id = R.string.app_name),
					fontSize = 12.sp,
					fontWeight = FontWeight.Light,
					modifier = Modifier
						.align(Alignment.CenterVertically)
						.padding(start = 8.dp)
				)
			}

		}
	}

}

@Preview
@Composable
fun TransactionDetailScreenPreview() {
	TransactionDetailScreen(
		Transaction(
			title = "Transfer",
			date = "19/01/2023",
			amount = 100.0,
			paymentMethod = "Credit Card",
			cardNumber = "**** **** **** 4570",
			cardProvider = "Visa",
			transactionNumber = 65483920198
		)
	)
}

@Composable
fun DashedDivider(
	thickness: Dp,
	color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
	phase: Float = 10f,
	intervals: FloatArray = floatArrayOf(10f, 10f),
	modifier: Modifier
) {
	Canvas(
		modifier = modifier
	) {
		val dividerHeight = thickness.toPx()
		drawRoundRect(
			color = color, style = Stroke(
				width = dividerHeight, pathEffect = PathEffect.dashPathEffect(
					intervals = intervals, phase = phase
				)
			)
		)
	}
}