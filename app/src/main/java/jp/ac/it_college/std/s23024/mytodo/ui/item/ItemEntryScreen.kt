package jp.ac.it_college.std.s23024.mytodo.ui.item

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.it_college.std.s23024.mytodo.R
import jp.ac.it_college.std.s23024.mytodo.ui.TodoTopAppBar
import jp.ac.it_college.std.s23024.mytodo.ui.navigation.NavigationDestination

object ItemEntryDestination : NavigationDestination {
    override val route: String = "item_entry"
    override val titleRes: Int = R.string.item_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onNavigateUp: () -> Unit = {},
    canNavigateBack: Boolean = true
    ) {
    Scaffold(
        topBar = {
            TodoTopAppBar(
                title = stringResource(ItemEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Text(
        modifier = Modifier.padding(innerPadding),
            text = "ここに新規入力画面を表示"
        )
    }
}

@Preview
@Composable
private fun ItemEntryScreenPreview() {
    ItemEntryScreen()
}