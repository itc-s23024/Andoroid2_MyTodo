package jp.ac.it_college.std.s23024.mytodo.deta

import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getAllItemsStream(): Flow<List<Item>>
    fun getItemStream(id: Int) : Flow<Item?>
    suspend fun insertItem(item: Item)
    suspend fun deleteItem(item: Item)
    suspend fun updateItem(item: Item)
}
