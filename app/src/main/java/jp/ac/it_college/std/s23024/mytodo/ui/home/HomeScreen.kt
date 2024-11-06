package jp.ac.it_college.std.s23024.mytodo.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.it_college.std.s23024.mytodo.R
import jp.ac.it_college.std.s23024.mytodo.ui.TodoTopAppBar
import jp.ac.it_college.std.s23024.mytodo.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToItemEntry: () -> Unit = {},
    navigateToItemUpdate: (Int) -> Unit = {},
    ) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TodoTopAppBar(
                title = stringResource(id = R.string.app_name),
                canNavigateBack = false,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_large)),
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.item_entry_title)
                )
            }
        }
    ) { innerPadding ->
        Button(
            modifier = Modifier.padding(innerPadding),
            onClick = {
                navigateToItemUpdate(1)
            }
        ) {
            Text(text = "更新画面を開くテスト")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()

}