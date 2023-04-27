package com.example.vinilos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilos.ui.adapters.AlbumsAdapter
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class TodoDetailsActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    fun clickForAddData() {
        onView(withId(R.id.navigation_album)).perform(click())
    }

    @After
    fun tearDown() {
        //clean up code
    }
}