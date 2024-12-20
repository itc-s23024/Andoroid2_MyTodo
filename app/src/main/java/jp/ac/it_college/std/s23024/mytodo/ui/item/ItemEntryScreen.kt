package jp.ac.it_college.std.s23024.mytodo.ui.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.ac.it_college.std.s23024.mytodo.R
import jp.ac.it_college.std.s23024.mytodo.deta.Item
import jp.ac.it_college.std.s23024.mytodo.deta.ItemsRepository
import jp.ac.it_college.std.s23024.mytodo.ui.TodoTopAppBar
import jp.ac.it_college.std.s23024.mytodo.ui.navigation.NavigationDestination
import jp.ac.it_college.std.s23024.mytodo.ui.theme.AppViewModelProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

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
    canNavigateBack: Boolean = true,
    viewModel: ItemEntryViewModel =
        viewModel(factory = AppViewModelProvider.Factory)
    ) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TodoTopAppBar(
                title = stringResource(ItemEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun ItemEntryScreenPreview() {
    val mockObject = object : ItemsRepository {
        override fun getAllItemsStream(): Flow<List<Item>> = emptyFlow()
        override fun getItemStream(id: Int): Flow<Item?> = emptyFlow()
        override suspend fun insertItem(item: Item) {}
        override suspend fun deleteItem(item: Item) {}
        override suspend fun updateItem(item: Item) {}
    }
    val viewModel =
        ItemEntryViewModel(itemsRepository = mockObject)
            .apply {
                updateUiState(
                    ItemDetails(
                        title = "タイトル",
                        description = "詳細",
                        done = true
                    )
                )
            }
    ItemEntryScreen(viewModel = viewModel)
}