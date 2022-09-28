package com.nbw.booksearchapp.ui.viewmodel

import androidx.lifecycle.*
import com.nbw.booksearchapp.data.model.SearchResponse
import com.nbw.booksearchapp.data.repository.BookSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookSearchViewModel(
    private val bookSearchRepository: BookSearchRepository,
    private val savedSateHandle: SavedStateHandle
) : ViewModel() {

    // API
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = bookSearchRepository.searchBooks(query, "accuracy", 1, 15)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    // SaveState
    var query = String()
        set(value) {
            field = value
            savedSateHandle.set(SAVE_STATE_KEY, value)
        }

    init {
        query = savedSateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }
}