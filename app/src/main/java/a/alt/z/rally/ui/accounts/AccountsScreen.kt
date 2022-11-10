package a.alt.z.rally.ui.accounts

import a.alt.z.rally.R
import a.alt.z.rally.data.UserData
import a.alt.z.rally.designsystem.component.AccountRow
import a.alt.z.rally.designsystem.component.RallyDetailsScreen
import a.alt.z.rally.designsystem.component.formatAccountNumber
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun AccountsScreen() {
    val totalBalance = UserData.accounts.map { it.balance }.sum()

    RallyDetailsScreen(
        Modifier,
        UserData.accounts,
        { account -> account.color },
        { account -> account.balance },
        totalBalance,
        stringResource(id = R.string.accounts)
    ) { account ->
        with(account) {
            val number = stringResource(R.string.account_redacted) + formatAccountNumber(number)

            AccountRow(Modifier, name, number, balance, color)
        }
    }
}

