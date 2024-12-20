package jp.ac.it_college.std.s23024.mytodo.ui.theme

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import jp.ac.it_college.std.s23024.mytodo.TodoApplication
import jp.ac.it_college.std.s23024.mytodo.ui.home.HomeViewModel
import jp.ac.it_college.std.s23024.mytodo.ui.item.ItemEditViewModel
import jp.ac.it_college.std.s23024.mytodo.ui.item.ItemEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(todoApplication().container.itemsRepository)
        }
        initializer {
            ItemEntryViewModel(todoApplication().container.itemsRepository)
        }
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle(),
                todoApplication().container.itemsRepository
            )
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)