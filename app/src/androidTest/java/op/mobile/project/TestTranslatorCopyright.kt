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
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestTranslatorCopyright {

    @Test
    fun testTranslatorCopyright() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val country = "China"

        // choose country
        onView(withId(R.id.spinner)).perform(click())

        // select country from spinner
        onData(
            Matchers.allOf(
                Matchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                Matchers.`is`(country)
            )
        ).perform(ViewActions.click())

        // click spinner option
        onView(withId(R.id.navigation_translator)).perform(click())

        // check spinner is showing up
        onView(withId(R.id.tv_copyright)).check(matches(isDisplayed()))

        // check the copyright text
        onView(withId(R.id.tv_copyright)).check(matches(withText(R.string.translated_by_yandex_translate)))

    }

}