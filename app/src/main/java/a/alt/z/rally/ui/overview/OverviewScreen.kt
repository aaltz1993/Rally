package a.alt.z.rally.ui.overview

import a.alt.z.rally.R
import a.alt.z.rally.designsystem.component.AccountRow
import a.alt.z.rally.designsystem.component.BillRow
import a.alt.z.rally.designsystem.component.formatAccountNumber
import a.alt.z.rally.designsystem.component.formatAmount
import a.alt.z.rally.data.UserData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun OverviewScreen() {
    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Overview Screen" }
    ) {
        AccountsCard { /* SEE ALL */ }

        Spacer(Modifier.height(RallyDefaultPadding))

        BillsCard { /* SEE ALL */ }
    }
}

private val RallyDefaultPadding = 12.dp
private const val SHOW_ITEM_COUNT = 3

@Composable
private fun <T> OverviewCard(
    title: String,
    amount: Float,
    data: List<T>,
    valueOf: (T) -> Float,
    colorOf: (T) -> Color,
    row: @Composable (T) -> Unit,
    onClickSeeAll: () -> Unit
) {
    Card {
        Column {
            Column(Modifier.padding(RallyDefaultPadding)) {
                Text(title, style = MaterialTheme.typography.subtitle2)

                val amountText = "$" + formatAmount(amount)
                Text(amountText, style = MaterialTheme.typography.h2)
            }

            OverviewDivider(data, valueOf, colorOf)

            Column(Modifier.padding(start = 16.dp, end = 8.dp, top = 4.dp)) {
                data
                    .take(SHOW_ITEM_COUNT)
                    .forEach { row(it) }

                SeeAllButton(onClick = onClickSeeAll)
            }
        }
    }
}

@Composable
private fun <T> OverviewDivider(
    data: List<T>,
    valueOf: (T) -> Float,
    colorOf: (T) -> Color
) {
    Row(Modifier.fillMaxWidth()) {
        data.forEach { item ->
            Spacer(
                modifier = Modifier
                    .weight(valueOf(item))
                    .height(1.dp)
                    .background(colorOf(item))
            )
        }
    }
}

@Composable
private fun AccountsCard(onClickSeeAll: () -> Unit) {
    val totalBalance = UserData.accounts.map { it.balance }.sum()

    OverviewCard(
        title = stringResource(id = R.string.accounts),
        amount = totalBalance,
        data = UserData.accounts,
        valueOf = { it.balance },
        colorOf = { it.color },
        row = { account ->
            with(account) {
                val number = stringResource(R.string.account_redacted) + formatAccountNumber(number)

                AccountRow(Modifier, name, number, balance, color)
            }
        },
        onClickSeeAll = onClickSeeAll
    )
}

@Composable
private fun BillsCard(onClickSeeAll: () -> Unit) {
    val totalAmount = UserData.bills.map { it.amount }.sum()

    OverviewCard(
        title = stringResource(id = R.string.bills),
        amount = totalAmount,
        data = UserData.bills,
        valueOf = { it.amount },
        colorOf = { it.color },
        row = { bill ->
            with(bill) {
                BillRow(Modifier, color, name, due, amount)
            }
        },
        onClickSeeAll = onClickSeeAll
    )
}

@Composable
private fun SeeAllButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth()
    ) {
        Text(stringResource(R.string.see_all))
    }
}