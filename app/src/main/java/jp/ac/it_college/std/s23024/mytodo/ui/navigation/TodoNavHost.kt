package jp.ac.it_college.std.s23024.mytodo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jp.ac.it_college.std.s23024.mytodo.ui.home.HomeDestination
import jp.ac.it_college.std.s23024.mytodo.ui.home.HomeScreen
import jp.ac.it_college.std.s23024.mytodo.ui.item.ItemEditDestination
import jp.ac.it_college.std.s23024.mytodo.ui.item.ItemEditScreen
import jp.ac.it_college.std.s23024.mytodo.ui.item.ItemEntryDestination
import jp.ac.it_college.std.s23024.mytodo.ui.item.ItemEntryScreen

@Composable
fun TodoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeDestination.route
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(ItemEntryDestination.route)
                },
                navigateToItemUpdate = {
                    navController.navigate(
                        "${ItemEditDestination.route}/${it}"
                    )
                }
            )
        }

        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(
                navigateBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(
                navArgument(ItemEditDestination.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            ItemEditScreen(
                navigateBack = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}