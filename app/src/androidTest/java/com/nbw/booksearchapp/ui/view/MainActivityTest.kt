package com.nbw.booksearchapp.ui.view

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nbw.booksearchapp.R
import com.nbw.booksearchapp.ui.adapter.BookSearchViewHolder
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test

/**
 * MainActivity의 라이프 사이클 상태 테스트
 */
//@RunWith(AndroidJUnit4::class) // test를 수행할 러너 설정
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    @SmallTest
    fun test_activity_state() {
        val activityState = activityScenarioRule.scenario.state.name
        assertThat(activityState).isEqualTo("RESUMED")
    }

    // UI Test
    @Test
    @LargeTest
    fun from_SearchFragment_to_FavoriteFragment_Ui_Operation() {
        // 1. SearchFragment
        // 1-1) 리사이클러뷰 대신 "No Result"가 출력되는지 확인
        Espresso.onView(ViewMatchers.withId(R.id.tv_emptylist))
            .check(ViewAssertions.matches(ViewMatchers.withText("No Result")))
        // 1-2) 검색어로 "android"를 입력
        Espresso.onView(ViewMatchers.withId(R.id.et_search))
            .perform(ViewActions.typeText("android"))
        // (1-2의 네트워크 동작이 끝날때까지 대기해야됨 - waitFor()를 사용)
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(3000))
        // 1-3) 리사이클러뷰 표시를 확인
        Espresso.onView(ViewMatchers.withId(R.id.rv_search_result))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        // 1-4) 첫번째 반환값을 클릭
        Espresso.onView(ViewMatchers.withId(R.id.rv_search_result))
            .perform(actionOnItemAtPosition<BookSearchViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(1000))
        // 1-5) BookFragment 결과를 저장
        Espresso.onView(ViewMatchers.withId(R.id.fab_favorite))
            .perform(ViewActions.click())
        // 1-6) 이전 화면으로 돌아감
        Espresso.pressBack()
        // 1-7) SnackBar가 사라질 때까지 대기
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(3000))
        Espresso.onView(ViewMatchers.withId(R.id.rv_search_result))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // 2. FavoriteFragment
        // 2-1) FavoriteFragment로 이동
        Espresso.onView(ViewMatchers.withId(R.id.fragment_favorite))
            .perform(ViewActions.click())
        // 2-2) 리사이클러뷰 표시를 확인
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorite_books))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        // 2-3) 첫번째 아이템을 슬라이드하여 삭제
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorite_books)).perform(
            actionOnItemAtPosition<BookSearchViewHolder>(0, ViewActions.swipeLeft())
        )
    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()

            override fun getDescription(): String = "wait for $delay milliseconds"

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }

        }
    }

}