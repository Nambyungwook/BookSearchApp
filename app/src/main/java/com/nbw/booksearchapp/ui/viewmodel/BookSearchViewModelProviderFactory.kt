package com.nbw.booksearchapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbw.booksearchapp.data.repository.BookSearchRepository

@Suppress("UNCHECKED_CAST")
class BookSearchViewModelProviderFactory(
    private val booksSearchRepository: BookSearchRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
            return BookSearchViewModel(booksSearchRepository) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}