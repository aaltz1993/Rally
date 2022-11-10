package a.alt.z.rally

import a.alt.z.rally.ui.accounts.AccountsScreen
import a.alt.z.rally.ui.bills.BillsScreen
import a.alt.z.rally.ui.overview.OverviewScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(Overview.route) { OverviewScreen() }

        composable(Accounts.route) { AccountsScreen() }

        composable(Bills.route) { BillsScreen() }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = navigate(route) {
    popUpTo(graph.findStartDestination().id) {
        saveState = true
    }

    launchSingleTop = true

    restoreState = true
}