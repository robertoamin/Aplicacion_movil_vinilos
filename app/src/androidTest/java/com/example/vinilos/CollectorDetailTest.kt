package com.example.vinilos

import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilos.ui.home.HomeActivity
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TodoCollectorDetailActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(HomeActivity::class.java)

    private fun getTextFromRecyclerViewItem(@IdRes recyclerViewId: Int, position: Int, @IdRes viewId: Int): String {
        val itemText = arrayOf<String?>(null)
        onView(withId(recyclerViewId)).check { view, _ ->
            if (view is RecyclerView) {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                val itemView = viewHolder?.itemView
                val textView = itemView?.findViewById<TextView>(viewId)
                itemText[0] = textView?.text?.toString()
            }
        }
        return itemText[0] ?: ""
    }

    @Before
    fun setUp() {
        onView(withId(R.id.card_coleccionistas)).perform(click())
        Thread.sleep(1000)

    }

    @Test
    fun navFromHome() {

        // Wait for the RecyclerView to appear
        onView(withId(R.id.list))
            .check(matches(isDisplayed()))

        // Get the collector name from the first element of the list
        val collectorName = getTextFromRecyclerViewItem(R.id.list, 0, R.id.collecor_list_name)

        // Click on first element of the list
        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        Thread.sleep(1000)

        // Compare the collectorName with the title of the top bar
        onView(allOf(withText(collectorName), withParent(withId(R.id.topAppBar)))).check(matches(isDisplayed()))

    }


    @After
    fun tearDown() {
        //clean up code
    }
}