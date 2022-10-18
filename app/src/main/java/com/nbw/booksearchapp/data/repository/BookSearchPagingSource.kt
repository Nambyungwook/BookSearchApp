package com.nbw.booksearchapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nbw.booksearchapp.data.api.BookSearchApi
import com.nbw.booksearchapp.data.model.Book
import com.nbw.booksearchapp.util.Constants.PAGING_SIZE
import retrofit2.HttpException
import java.io.IOException

class BookSearchPagingSource(
    private val api: BookSearchApi,
    private val query: String,
    private val sort: String
    // PagingSource<페이지 번호, 들어갈 데이터 타입>
) : PagingSource<Int, Book>() {

    // 여러가지 이유로 페이지를 갱신해야할때 사용되는 함수
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // Pager가 데이터를 호출 할때마다 불리는 함수
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val pageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = api.searchBooks(query, sort, pageNumber, params.loadSize)
            val endOfPaginactionReached = response.body()?.meta?.isEnd!!

            val data = response.body()?.documents!!
            val prevKey = if (pageNumber == STARTING_PAGE_INDEX) null else pageNumber - 1
            val nextKey = if (endOfPaginactionReached) {
                null
            } else {
                pageNumber + (params.loadSize / PAGING_SIZE)
            }
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}