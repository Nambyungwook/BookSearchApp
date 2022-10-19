package com.nbw.booksearchapp.ui.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Robolectric
 * JVM에서 사용되기 때문에 더 빠른 테스트를 수행할 수 있음
 */
//@RunWith(RobolectricTestRunner::class)
//class MainActivityTest {
//
//    @Test
//    @SmallTest
//    fun test_activity_state() {
//        val controller = Robolectric.setupActivity(MainActivity::class.java)
//        val activityState = controller.lifecycle.currentState.name
//        assertThat(activityState).isEqualTo("RESUMED")
//    }
//}

// AndroidX 테스트를 사용하여 Robolectric 테스트를 사용하든 계측 테스트를 작성하든 상관없이 AndroidJUnit4 에서 통합됨
@RunWith(AndroidJUnit4::class) // test를 수행할 러너 설정
class MainActivityTest {
    
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