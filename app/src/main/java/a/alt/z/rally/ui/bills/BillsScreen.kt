package a.alt.z.rally.ui.bills

import a.alt.z.rally.R
import a.alt.z.rally.data.UserData
import a.alt.z.rally.designsystem.component.BillRow
import a.alt.z.rally.designsystem.component.RallyDetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun BillsScreen() {
    val totalAmount = UserData.bills.map { it.amount }.sum()

    RallyDetailsScreen(
        Modifier,
        UserData.bills,
        { account -> account.color },
        { account -> account.amount },
        totalAmount,
        stringResource(id = R.string.bills)
    ) { bill ->
        with(bill) {
            BillRow(Modifier, color, name, due, amount)
        }
    }
}