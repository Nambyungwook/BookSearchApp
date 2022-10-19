package com.nbw.booksearchapp.ui.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * MainActivity의 라이프 사이클 상태 테스트
 */
@RunWith(AndroidJUnit4::class) // test를 수행할 러너 설정
class MainActivityTest {

//    private lateinit var activityScenario: ActivityScenario<MainActivity>
//
//    @Before
//    fun setUp() {
//        // 액티비티 시나리오를 통해 테스트용 액티비티 생성
//        activityScenario = ActivityScenario.launch(MainActivity::class.java)
//    }
//
//    @After
//    fun tearDown() {
//        // 액티비티 시나리오 파괴
//        activityScenario.close()
//    }

    // 위에 주석처리된 부분이 매번 작성하면 번거롭기 때문에 시나리오 룰을 이용하여 작성
    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    @SmallTest
    fun test_activity_state() {
        val activityState = activityScenarioRule.scenario.state.name
        assertThat(activityState).isEqualTo("RESUMED")
    }
}