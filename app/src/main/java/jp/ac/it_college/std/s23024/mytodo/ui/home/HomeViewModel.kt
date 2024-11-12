package jp.ac.it_college.std.s23024.mytodo.ui.home

import androidx.lifecycle.ViewModel
import jp.ac.it_college.std.s23024.mytodo.deta.Item
import jp.ac.it_college.std.s23024.mytodo.deta.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeUiState(
    val itemList: Flow<List<Item>> = flowOf(listOf())
)

class HomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {
    val homeUiState = HomeUiState(itemsRepository.getAllItemsStream())
}
