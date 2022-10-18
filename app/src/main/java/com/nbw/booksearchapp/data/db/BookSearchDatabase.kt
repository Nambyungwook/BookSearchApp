package com.nbw.booksearchapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nbw.booksearchapp.data.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(OrmConverter::class) // Converter를 등록하여 알아서 변환함
abstract class BookSearchDatabase : RoomDatabase() {

    abstract fun bookSearchDao(): BookSearchDao

    // Hilt DI 사용으로 AppModule에서 BookSearchDatabase를 싱글턴으로 주입할 수 있으므로 아래 코드는 사용하지 않음
//    companion object {
//        @Volatile
//        private var INSTANCE: BookSearchDatabase? = null
//
//        private fun buildDatabase(context: Context): BookSearchDatabase =
//            Room.databaseBuilder(
//                context.applicationContext,
//                BookSearchDatabase::class.java,
//                "favorite-books"
//            ).build()
//
//        fun getInstance(context: Context): BookSearchDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//    }
}