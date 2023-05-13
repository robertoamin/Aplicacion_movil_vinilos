package com.example.vinilos

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilos.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BandDetailsTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun clickForBandDetail() {
        onView(withId(R.id.card_usuarios)).perform(click())

        //go to Band/artist section
        onView(withId(R.id.navigation_notifications)).perform(click())
        // Wait for Band/artist list to be displayed
        onView(withId(R.id.list)).check(matches(isDisplayed()))

        // Scroll up to desired position in the recyclerview
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Thread.sleep(3000)
        // Click on first objet of RecyclerView list
        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        // wait to show album details
        Thread.sleep(3000)
    }



}