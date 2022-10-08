package com.nbw.booksearchapp.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.nbw.booksearchapp.data.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun getFavoriteBooks(): Flow<List<Book>>

    // Room은 Flow가 아니라 PagingSoure 타입으로 데이터를 반환할 수 있음
    @Query("SELECT * FROM books")
    fun getFavoritePagingBooks(): PagingSource<Int, Book>
}