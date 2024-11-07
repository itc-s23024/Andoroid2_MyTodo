package jp.ac.it_college.std.s23024.mytodo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import jp.ac.it_college.std.s23024.mytodo.ui.navigation.TodoNavHost

@Composable
fun TodoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    TodoNavHost(navController = navController)
}