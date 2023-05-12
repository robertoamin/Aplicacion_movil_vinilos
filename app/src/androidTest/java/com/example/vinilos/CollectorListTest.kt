package com.example.vinilos

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilos.ui.home.HomeActivity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.thread

@RunWith(AndroidJUnit4::class)
class TodoCollectorsActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun navFromHome() {
        val image = onView(withId(R.id.card_coleccionistas))
        image.perform(click())
        Thread.sleep(1000)
        // Wait for the RecyclerView to appear
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))

        val textAlbum = onView(withText("Roberto Amin"))
        textAlbum.check(matches(isDisplayed()))

    }

    @Test
    fun navFromMain(){
        val image = onView(withId(R.id.card_usuarios))
        image.perform(click())
        Thread.sleep(1000)
        // Wait for the RecyclerView to appear
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))

        onView(withId(R.id.navigation_collector)).perform(click())

        val textAlbum = onView(withText("Roberto Amin"))
        textAlbum.check(matches(isDisplayed()))
    }


    @After
    fun tearDown() {
        //clean up code
    }
}