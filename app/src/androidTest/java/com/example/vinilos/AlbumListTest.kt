package com.example.vinilos

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilos.ui.home.HomeActivity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDetailsActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun navFromHomeToAlbum() {
        val image = onView(withId(R.id.card_usuarios))
        image.perform(click())
        Thread.sleep(1000)

        // Wait for the RecyclerView to appear
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))

        // Count the number of albums in the RecyclerView
        val albumCount = getRecyclerViewItemCount(R.id.list)

        // Perform assertions on the album count
        // For example, assert that the count is greater than zero
        assertTrue(albumCount > 0)
    }

    @Test
    fun navFromCollectorToAlbum() {
        val image = onView(withId(R.id.card_coleccionistas))
        image.perform(click())
        Thread.sleep(1000)

        onView(withId(R.id.navigation_album)).perform(click())
        Thread.sleep(1000)

        // Wait for the RecyclerView to appear
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))

        // Count the number of albums in the RecyclerView
        val albumCount = getRecyclerViewItemCount(R.id.list)

        // Perform assertions on the album count
        // For example, assert that the count is greater than zero
        assertTrue(albumCount > 0)
    }

    private fun getRecyclerViewItemCount(@IdRes recyclerViewId: Int): Int {
        val itemCount = intArrayOf(0)
        onView(withId(recyclerViewId)).check { view, _ ->
            if (view is RecyclerView) {
                itemCount[0] = view.adapter?.itemCount ?: 0
            }
        }
        return itemCount[0]
    }

    @After
    fun tearDown() {
        Thread.sleep(1000)
        //clean up code
    }
}