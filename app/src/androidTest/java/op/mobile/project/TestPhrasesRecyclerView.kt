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
class TestPhrasesRecyclerView {

    @Test
    fun testPhrasesRecyclerView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // choose country
        onView(withId(R.id.spinner)).perform(click())

        // select New Zealand from spinner
        onData(
            Matchers.allOf(
                Matchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                Matchers.`is`("New Zealand")
            )
        ).perform(ViewActions.click())

        onView(withId(R.id.navigation_phrases)).perform(click())

        // check recycler view is showing up
        onView(withId(R.id.rv_phrases))
            .check(matches(isDisplayed()))

    }

}