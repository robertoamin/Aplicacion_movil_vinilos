package com.example.vinilos

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
    fun clickForAddData() {
        val image = onView(withId(R.id.card_usuarios))
        image.perform(click())
        Thread.sleep(6000)
        // Wait for the RecyclerView to appear
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))

        // Count the number of albums in the RecyclerView
        //val albumCount = getAlbumCount(R.id.list)

        // Perform assertions on the album count
        // For example, assert that the count is greater than zero
        //assertTrue(albumCount > 0)
        val textAlbum = onView(withText("parental advisory"))
        textAlbum.check(matches(isDisplayed()))

        /*Thread.sleep(3000)
        onView(withId(R.id.card)).perform(click())
        //val items = onData(allOf(withId(R.id.card), withText("card"))).toList()
        Thread.sleep(3000)*/
    }

    private fun getAlbumCount(recyclerViewId: Int): Int {
        var albumCount = 0
        mActivityRule?.scenario?.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(recyclerViewId)
            recyclerView.adapter?.let { adapter ->
                albumCount = adapter.itemCount
            }
        }
        return albumCount
    }


    @After
    fun tearDown() {
        //clean up code
    }
}