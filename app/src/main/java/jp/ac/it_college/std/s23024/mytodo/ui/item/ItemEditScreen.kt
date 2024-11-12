package jp.ac.it_college.std.s23024.mytodo.ui.item

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.ac.it_college.std.s23024.mytodo.R
import jp.ac.it_college.std.s23024.mytodo.deta.Item
import jp.ac.it_college.std.s23024.mytodo.deta.ItemsRepository
import jp.ac.it_college.std.s23024.mytodo.ui.TodoTopAppBar
import jp.ac.it_college.std.s23024.mytodo.ui.dialog.DeleteItemDialog
import jp.ac.it_college.std.s23024.mytodo.ui.navigation.NavigationDestination
import jp.ac.it_college.std.s23024.mytodo.ui.theme.AppViewModelProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

object ItemEditDestination : NavigationDestination {
    override val route: String = "item_edit"
    override val titleRes: Int = R.string.edit_item_title
        const val itemIdArg = "itemId"
        val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: ItemEditViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TodoTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ItemEntryBody(
            modifier = Modifier.padding(innerPadding),
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateItem()
                }
                navigateBack()
            },
            showDelete = true,
            onDeleteClick = { showDialog = true }
        )
    }
    if (showDialog) {
        DeleteItemDialog(onConfirm = {
            coroutineScope.launch {
                viewModel.deleteItem()
            }
            navigateBack()
        },
            onDismiss = {
                showDialog = false
            }
        )
    }
}

@Preview
@Composable
private fun ItenEditScreenPreview() {
    val mockObject = object : ItemsRepository {
        override fun getAllItemsStream(): Flow<List<Item>> = emptyFlow()
        override fun getItemStream(id: Int): Flow<Item?> = MutableStateFlow(
            Item(1,"牛乳を買う","2カートン",false)
        )
        override suspend fun insertItem(item: Item) {}
        override suspend fun deleteItem(item: Item) {}
        override suspend fun updateItem(item: Item) {}
    }
    ItemEditScreen(
        viewModel = ItemEditViewModel(
            savedStateHandle = SavedStateHandle(mapOf("itemId" to 1)),
            itemsRepository = mockObject
        )
    )
}