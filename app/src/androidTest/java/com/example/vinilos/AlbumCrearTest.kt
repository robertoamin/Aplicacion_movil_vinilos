import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilos.ui.home.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions


@RunWith(AndroidJUnit4::class)
class AlbumCrearTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    // Positive Test for AlbumCreation
    @Test
    fun clickForCrearAlbum() {
        onView(withId(R.id.card_usuarios)).perform(click())

        onView(withId(R.id._action_button)).perform(click())

        // Wait until Album fields appears
        onView(withId(R.id.editTextNombre)).check(matches(isDisplayed()))

        // fill data in album form
        onView(withId(R.id.editTextNombre)).perform(typeText("El Universal"), closeSoftKeyboard())
        onView(withId(R.id.editTextDescripcion)).perform(typeText("Musica de todos los tiempos..."), closeSoftKeyboard())
        onView(withId(R.id.editTextCover)).perform(typeText("https://assets.turbologo.com/blog/es/2022/04/07043402/06-958x575.png"), closeSoftKeyboard())
        onView(withId(R.id.editTextReleasedate)).perform(typeText("2010"), closeSoftKeyboard())
        onView(withId(R.id.editTextGenre)).perform(replaceText("Rock"), closeSoftKeyboard())
        onView(withId(R.id.editTextRecordlabel)).perform(replaceText("Sony Music"), closeSoftKeyboard())

        //Click on Save button
        onView(withId(R.id.buttonGuardar)).perform(click())

        // Wait until list is displayed
        onView(withId(R.id.list)).check(matches(isDisplayed()))

        // Total items in RecyclerView
        val itemCount = getRecyclerViewItemCount(R.id.list)

        // move to last position on RecyclerView
        onView(withId(R.id.list)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))

        // Search for "El Universal" in the list
        onView(withText("El Universal")).check(matches(isDisplayed()))
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

    // Negative Test for AlbumCreation without name field filled
    @Test
    fun clickForCrearAlbumNegative1() {
        onView(withId(R.id.card_usuarios)).perform(click())
        onView(withId(R.id._action_button)).perform(click())
        // Wait until Album fields appears
        onView(withId(R.id.editTextNombre)).check(matches(isDisplayed()))
        // fill data in album form, with name in blank
        onView(withId(R.id.editTextNombre)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.editTextDescripcion)).perform(typeText("Musica de todos los tiempos..."), closeSoftKeyboard())
        onView(withId(R.id.editTextCover)).perform(typeText("https://assets.turbologo.com/blog/es/2022/04/07043402/06-958x575.png"), closeSoftKeyboard())
        onView(withId(R.id.editTextReleasedate)).perform(typeText("2010"), closeSoftKeyboard())
        onView(withId(R.id.editTextGenre)).perform(replaceText("Rock"), closeSoftKeyboard())
        onView(withId(R.id.editTextRecordlabel)).perform(replaceText("Sony Music"), closeSoftKeyboard())
        //Click on Save button
        onView(withId(R.id.buttonGuardar)).perform(click())
        // Verify if the error toast message is displayed
    }

    // Negative Test for AlbumCreation with cancel button instead of save button
    @Test
    fun clickForCrearAlbumNegative2() {
        onView(withId(R.id.card_usuarios)).perform(click())
        onView(withId(R.id._action_button)).perform(click())
        // Wait until Album fields appears
        onView(withId(R.id.editTextNombre)).check(matches(isDisplayed()))
        // fill data in album form, with name in blank
        onView(withId(R.id.editTextNombre)).perform(typeText("libelula"), closeSoftKeyboard())
        onView(withId(R.id.editTextDescripcion)).perform(typeText("Musica de todos los tiempos..."), closeSoftKeyboard())
        onView(withId(R.id.editTextCover)).perform(typeText("https://assets.turbologo.com/blog/es/2022/04/07043402/06-958x575.png"), closeSoftKeyboard())
        onView(withId(R.id.editTextReleasedate)).perform(typeText("2010"), closeSoftKeyboard())
        onView(withId(R.id.editTextGenre)).perform(replaceText("Rock"), closeSoftKeyboard())
        onView(withId(R.id.editTextRecordlabel)).perform(replaceText("Sony Music"), closeSoftKeyboard())
        //Click on Save button
        onView(withId(R.id.buttonCancelar)).perform(click())
        // Wait until list is displayed
        onView(withId(R.id.list)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }

    // Negative Test for AlbumCreation with wrong data in ReleaseDate field
    @Test
    fun clickForCrearAlbumNegative3() {
        onView(withId(R.id.card_usuarios)).perform(click())
        onView(withId(R.id._action_button)).perform(click())
        // Wait until Album fields appears
        onView(withId(R.id.editTextNombre)).check(matches(isDisplayed()))
        // fill data in album form, with name in blank
        onView(withId(R.id.editTextNombre)).perform(typeText("libelula"), closeSoftKeyboard())
        onView(withId(R.id.editTextDescripcion)).perform(typeText("Musica de todos los tiempos..."), closeSoftKeyboard())
        onView(withId(R.id.editTextCover)).perform(typeText("https://assets.turbologo.com/blog/es/2022/04/07043402/06-958x575.png"), closeSoftKeyboard())
        onView(withId(R.id.editTextReleasedate)).perform(typeText("wrong"), closeSoftKeyboard())
        onView(withId(R.id.editTextGenre)).perform(replaceText("Rock"), closeSoftKeyboard())
        onView(withId(R.id.editTextRecordlabel)).perform(replaceText("Sony Music"), closeSoftKeyboard())
        //Click on Save button
        onView(withId(R.id.buttonGuardar)).perform(click())
        // Verify if the error toast message is displayed
        Thread.sleep(1000)
    }

    @Test
    fun clickForCrearAlbumNegative4() {
        onView(withId(R.id.card_usuarios)).perform(click())
        onView(withId(R.id._action_button)).perform(click())
        // Wait until Album fields appears
        onView(withId(R.id.editTextNombre)).check(matches(isDisplayed()))
        // fill data in album form, with name in blank
        onView(withId(R.id.editTextNombre)).perform(typeText("libelula"), closeSoftKeyboard())
        onView(withId(R.id.editTextDescripcion)).perform(typeText("Musica de todos los tiempos..."), closeSoftKeyboard())
        onView(withId(R.id.editTextCover)).perform(typeText("https://assets.turbologo.com/blog/es/2022/04/07043402/06-958x575.png"), closeSoftKeyboard())
        onView(withId(R.id.editTextReleasedate)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.editTextGenre)).perform(replaceText("Rock"), closeSoftKeyboard())
        onView(withId(R.id.editTextRecordlabel)).perform(replaceText("Sony Music"), closeSoftKeyboard())
        //Click on Save button
        onView(withId(R.id.buttonGuardar)).perform(click())
        // Verify if the error toast message is displayed
        Thread.sleep(1000)
    }

    // Negative Test for AlbumCreation without Cover field filled
    @Test
    fun clickForCrearAlbumNegative5() {
        onView(withId(R.id.card_usuarios)).perform(click())
        onView(withId(R.id._action_button)).perform(click())
        // Wait until Album fields appears
        onView(withId(R.id.editTextNombre)).check(matches(isDisplayed()))
        // fill data in album form, with name in blank
        onView(withId(R.id.editTextNombre)).perform(typeText("libelula"), closeSoftKeyboard())
        onView(withId(R.id.editTextDescripcion)).perform(typeText("Musica de todos los tiempos..."), closeSoftKeyboard())
        onView(withId(R.id.editTextCover)).perform(typeText(""), closeSoftKeyboard())
        onView(withId(R.id.editTextReleasedate)).perform(typeText("2010"), closeSoftKeyboard())
        onView(withId(R.id.editTextGenre)).perform(replaceText("Rock"), closeSoftKeyboard())
        onView(withId(R.id.editTextRecordlabel)).perform(replaceText("Sony Music"), closeSoftKeyboard())
        //Click on Save button
        onView(withId(R.id.buttonGuardar)).perform(click())
        // Verify if the error toast message is displayed
        Thread.sleep(1000)
    }

    @After
    fun tearDown() {
        //clean up code
    }

}