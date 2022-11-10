package a.alt.z.rally

import a.alt.z.rally.ui.accounts.AccountsScreen
import a.alt.z.rally.ui.bills.BillsScreen
import a.alt.z.rally.ui.overview.OverviewScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

interface RallyDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

object Overview : RallyDestination {
    override val icon: ImageVector = Icons.Filled.PieChart
    override val route: String = "overview"
    override val screen: @Composable () -> Unit = { OverviewScreen() }
}

object Accounts : RallyDestination {
    override val icon: ImageVector = Icons.Filled.AttachMoney
    override val route: String = "accounts"
    override val screen: @Composable () -> Unit = { AccountsScreen() }
}

object Bills : RallyDestination {
    override val icon: ImageVector = Icons.Filled.MoneyOff
    override val route: String = "bills"
    override val screen: @Composable () -> Unit = { BillsScreen() }
}

val rallyTabRowScreens = listOf(Overview, Accounts, Bills)