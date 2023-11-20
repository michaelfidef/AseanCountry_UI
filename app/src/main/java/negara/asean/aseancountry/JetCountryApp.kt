package negara.asean.aseancountry

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import negara.asean.aseancountry.ui.navigation.NavigationItem
import negara.asean.aseancountry.ui.navigation.Screen
import negara.asean.aseancountry.ui.screen.detail.DetailActivity
import negara.asean.aseancountry.ui.screen.profile.ProfileActivity
import negara.asean.aseancountry.ui.theme.AseanCountryTheme

@Composable
fun JetCountryApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailActivity.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                Home(
                    modifier = Modifier,
                    navigateToDetail = { Id ->
                        navController.navigate(Screen.DetailActivity.createRoute(Id))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileActivity()
            }
            composable(
                route = Screen.DetailActivity.route,
                arguments = listOf(navArgument("id") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("id") ?: -1L
                Log.d("MyCountry", "ID $id")
                DetailActivity(
//                    id = id,
//                    navigateBack ={
//                        navController.navigateUp()
//                    },
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    AseanCountryTheme {
        JetCountryApp()
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Place,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}