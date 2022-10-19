package com.nbw.booksearchapp.ui.viewmodel

import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.nbw.booksearchapp.data.model.Book
import com.nbw.booksearchapp.data.repository.FakeBookSearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@MediumTest
class BookViewModelTest {

    private lateinit var viewModel: BookViewModel

    @Before
    fun setUp() {
        // 프로덕션 코드에서는 뷰모델의 싱글턴을 보장해야하지만 테스트에서는 상관없음
        // FakeBookSearchRepository() 전달
        viewModel = BookViewModel(FakeBookSearchRepository())
    }

    @After
    fun tearDown() {

    }

    @Test
    @ExperimentalCoroutinesApi
    fun save_book_test() = runTest {
        val book = Book(
            listOf("a"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )
        viewModel.saveBook(book)

        val favoriteBooks = viewModel.favoriteBooks.first()
        assertThat(favoriteBooks).contains(book)
    }

}