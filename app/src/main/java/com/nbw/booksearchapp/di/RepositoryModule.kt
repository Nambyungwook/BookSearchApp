package com.nbw.booksearchapp.di

import com.nbw.booksearchapp.data.repository.BookSearchRepository
import com.nbw.booksearchapp.data.repository.BookSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 인터페이스인 Repository를 주입하기 위한 RepositoryModule
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBookSearchRepository(
        bookSearchRepositoryImpl: BookSearchRepositoryImpl
    ): BookSearchRepository
}