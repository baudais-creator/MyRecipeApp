package com.davidbaudais.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categorieState = mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categorieState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        // allows the suspend function to run in the background, asynchronously
        // this where the viewModel is launched.
        // viewModelScope allows us to launch coroutines in the viewModel
       viewModelScope.launch {
            try {
                val response = recipieService.getCategories()
                _categorieState.value = _categorieState.value.copy(
                    list = response.categories,
                    loading = false, // not loading anymore
                    error = null // fetch successful
                )

            } catch(e:Exception) {
                _categorieState.value = _categorieState.value.copy(
                    loading = false, // not loading anymore
                    error = "Error fetching categories ${e.message}")
            }
        }

    }
    // state of the recipe download, do we have a list of categories or not
    // if yes the list will be in val list: List<Category>; if not the list will be empty
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}