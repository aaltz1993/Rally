package a.alt.z.rally

import a.alt.z.rally.designsystem.component.RallyTabRow
import a.alt.z.rally.designsystem.theme.RallyTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()
        }
    }
}

@Composable
fun RallyApp() {
    RallyTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = rallyTabRowScreens.find { it.route == currentDestination?.route } ?: Overview

        Scaffold(
            topBar = {
                RallyTabRow(
                    screens = rallyTabRowScreens,
                    onTabSelected = { screen -> navController.navigateSingleTopTo(screen.route) },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding -> RallyNavHost(navController, Modifier.padding(innerPadding)) }
    }
}