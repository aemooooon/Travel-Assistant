package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.IndexActivity
import op.mobile.project.ui.activity.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestTextToSpeechFragment {

    @Test
    fun testTextToSpeechFragment() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val country = "France"
        val textToSpeech = "Hello world"

        // choose country
        onView(withId(R.id.spinner)).perform(click())

        // select France from spinner
        onData(
            Matchers.allOf(
                Matchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                Matchers.`is`(country)
            )
        ).perform(ViewActions.click())

        // click France option
        onView(withId(R.id.navigation_text_to_speech)).perform(click())

        // enter a text
        onView(withId(R.id.text_input)).perform(ViewActions.typeText(textToSpeech))

        // click speak button
        onView(withId(R.id.btn_speak)).perform(click())

        // clear text view content
        onView(withId(R.id.btn_reset)).perform(click())

        // check text view content equal to null
        onView(withId(R.id.text_input)).check(matches(withText("")))

    }

}