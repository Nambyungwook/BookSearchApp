package com.nbw.booksearchapp.util

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class CalculatorTest {
    // 단위 테스트에서 공유하면 안됨
//    private val calculator = Calculator()
    // 각 테스트 케이스별로 calculator를 공유하지 않도록 설정
    private lateinit var calculator: Calculator

    // 각 테스트의 실행 직전에 수행되어야 하는 작업
    @Before
    fun setUp() {
        calculator = Calculator()
    }

    // 각 테스트 종류 직후에 수행할 작업
    @After
    fun tearDown() {
        // 인스턴스의 삭제, DB가 열려있다면 닫는 작업 등
    }

    @Test
    fun `additional function test`() {
        // Given
        val x = 4
        val y = 2

        // When
        val result = calculator.addition(x, y)

        // Then
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun `subtraction function test`() {
        // Given
        val x = 4
        val y = 2

        // When
        val result = calculator.subtraction(x, y)

        // Then
        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `multiplication function test`() {
        // Given
        val x = 4
        val y = 2

        // When
        val result = calculator.multiplication(x, y)

        // Then
        assertThat(result).isEqualTo(8)
    }

    @Test
    fun `division function test`() {
        // Given
        val x = 4
        val y = 0

        // When
        val result = calculator.division(x, y)

        // Then
        assertThat(result).isEqualTo(null)
    }
}