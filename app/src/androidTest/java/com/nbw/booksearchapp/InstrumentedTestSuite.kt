package com.nbw.booksearchapp

import com.nbw.booksearchapp.data.db.BookSearchDaoTest
import com.nbw.booksearchapp.ui.view.MainActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * MainActitivyTest, BookSearchDaoTest(Room사용) 의 Test를 묶어서 할 수 있게 구현
 */
@RunWith(Suite::class)
@ExperimentalCoroutinesApi
@Suite.SuiteClasses(
    MainActivityTest::class,
    BookSearchDaoTest::class
)
class InstrumentedTestSuite {
}