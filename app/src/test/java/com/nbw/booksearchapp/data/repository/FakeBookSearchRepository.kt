package com.nbw.booksearchapp.data.repository

import androidx.paging.PagingData
import com.nbw.booksearchapp.data.model.Book
import com.nbw.booksearchapp.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

/**
 * 테스트에는 사용할 수 있지만 프로덕션에는 사용할 수 없는 Fake Test Double
 */
class FakeBookSearchRepository : BookSearchRepository {

    private val bookItems = mutableListOf<Book>()

    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<SearchResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun insertBooks(book: Book) {
        bookItems.add(book)
    }

    override suspend fun deleteBooks(book: Book) {
        bookItems.remove(book)
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return flowOf(bookItems)
    }

    override suspend fun saveSortMode(mode: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getSortMode(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCacheDeleteMode(mode: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getCacheDeleteMode(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
        TODO("Not yet implemented")
    }

    override fun searchBooksPaging(query: String, sort: String): Flow<PagingData<Book>> {
        TODO("Not yet implemented")
    }
}