package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
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
class TestTranslatorChangeLanguages {

    @Test
    fun testTranslatorChangeLanguages() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val country = "Brazil"
        val fromLanguage = "Maori"
        val toLanguage = "Zulu"

        // choose country
        onView(withId(R.id.spinner)).perform(ViewActions.click())

        // select New Zealand from spinner
        onData(
            Matchers.allOf(
                Matchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                Matchers.`is`(country)
            )
        ).perform(ViewActions.click())

        onView(withId(R.id.navigation_translator)).perform(ViewActions.click())

        onView(withId(R.id.from_lang)).perform(ViewActions.click())

        onData(
            Matchers.allOf(
                Matchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                Matchers.`is`(fromLanguage)
            )
        ).perform(ViewActions.click())

        onView(withId(R.id.to_lang)).perform(ViewActions.click())

        onData(
            Matchers.allOf(
                Matchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                Matchers.`is`(toLanguage)
            )
        ).perform(ViewActions.click())

    }

}