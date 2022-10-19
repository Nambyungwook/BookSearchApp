package com.nbw.booksearchapp.data.db

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nbw.booksearchapp.data.model.Book
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

// Hilt를 사용하여 테스트 코드 수정
//@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@SmallTest
class BookSearchDaoTest {

    //    private lateinit var database: BookSearchDatabase
    @Inject
    @Named("test_db") // TestAppModule에서 제공하는 BookSearchDatabase의 이름과 동일하게 설정 - Hilt가 의존성 주입시에 실제 프로덕트에서 사용하는 것과 헷갈리지 않도록 함
    lateinit var database: BookSearchDatabase
    private lateinit var dao: BookSearchDao

    // hilt에서 테스트 코드에 의존성 주입할수 있도록 Rule 설정
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
//        database = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            BookSearchDatabase::class.java
//        ).allowMainThreadQueries().build()
        // hilt에서 테스트 코드에 의존성 주입할수 있도록 Rule 설정
        hiltRule.inject()
        dao = database.bookSearchDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    @ExperimentalCoroutinesApi
    fun insert_book_to_db() = runTest {
        val book = Book(
            listOf("a"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )
        dao.insertBook(book)

        val favoriteBooks = dao.getFavoriteBooks().first()
        assertThat(favoriteBooks).contains(book)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun delete_book_to_db() = runTest {
        val book = Book(
            listOf("a"), "b", "c", "d", 0, "e",
            0, "f", "g", "h", listOf("i"), "j"
        )
        dao.insertBook(book)
        dao.deleteBook(book)

        val favoriteBooks = dao.getFavoriteBooks().first()
        assertThat(favoriteBooks).doesNotContain(book)
    }
}