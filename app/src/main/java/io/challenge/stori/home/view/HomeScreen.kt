package io.challenge.stori.home.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import io.challenge.stori.R
import io.challenge.stori.home.model.Transaction
import io.challenge.stori.home.repository.HomeRepositoryImpl
import io.challenge.stori.home.useCase.HomeUseCase
import io.challenge.stori.home.view.ui.BalanceCard
import io.challenge.stori.home.view.ui.ShimmerCompose
import io.challenge.stori.home.view.ui.TransactionCard
import io.challenge.stori.home.viewModel.HomeViewModel
import io.challenge.stori.movements.view.TransactionDetailScreen


class HomeActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			val homeViewModel = HomeViewModel(HomeUseCase(HomeRepositoryImpl()))
			homeViewModel.loadBankAccountData(intent.getStringExtra("userId") ?: "0")
			HomeScreen(homeViewModel)
		}
	}
}

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
	val bankAccountData by homeViewModel.bankAccountData.observeAsState()
	val totalBalance by homeViewModel.totalBalance.observeAsState()
	val negativeBalance by homeViewModel.negativeBalance.observeAsState()

	val showDialog = remember { mutableStateOf(false) }
	val mustChangeDialogBackground = remember { mutableStateOf(false) }
	val selectedItemLocation = remember { mutableStateOf<Transaction?>(null) }

	if (showDialog.value) {
		Box(
			modifier = Modifier.fillMaxSize()
		) {
			Dialog(
				onDismissRequest = {
					showDialog.value = false
					mustChangeDialogBackground.value = true
				}, properties = DialogProperties(
					dismissOnBackPress = true, dismissOnClickOutside = true
				)
			) {
				TransactionDetailScreen(selectedItemLocation.value)
			}
		}
	}

	if (bankAccountData == null) {
		ShimmerCompose()
	} else {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(16.dp)
		) {
			Spacer(modifier = Modifier.height(8.dp))

			BalanceCard(totalBalance = totalBalance ?: "0", todayBalance = negativeBalance ?: "0")

			Spacer(modifier = Modifier.height(12.dp))

			Text(
				text = stringResource(id = R.string.recent_movements),
				fontSize = 20.sp,
				fontWeight = FontWeight.Bold,
				color = Color.Gray,
				modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
			)

			if (bankAccountData.isNullOrEmpty()) {
				Text(
					text = stringResource(id = R.string.no_movements),
					color = Color.Gray,
					modifier = Modifier.padding(8.dp)
				)
			} else {
				LazyColumn(
					modifier = Modifier
						.fillMaxSize()
						.weight(1f)
				) {
					bankAccountData?.let {
						items(it.size) { transactions ->
							val index = it[transactions]
							key(transactions.hashCode()) {
								Box(
									modifier = Modifier.clickable {
										selectedItemLocation.value = index
										showDialog.value = true
									},
								) {
									TransactionCard(index)
								}
							}
						}
					}
				}
			}
		}
	}
}
