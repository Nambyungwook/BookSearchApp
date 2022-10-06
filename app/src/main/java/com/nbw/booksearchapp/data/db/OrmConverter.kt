package com.nbw.booksearchapp.data.db

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Book클래스에 val authors: List<String> 가 List<String>으로 Room에서 사용할 수 없는 데이터 타입
 *  Serialization으로 List<String> -> String 으로 변환하기 위해 필요한 클래스
 */
class OrmConverter {
    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}