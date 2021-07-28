package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestNavigateFromMainActivityToIndexActivity {

    @Test
    fun testNavigateFromMainActivityToIndexActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // click spinner
        Espresso.onView(ViewMatchers.withId(R.id.spinner)).perform(ViewActions.click())

        // choose Japan option
        Espresso.onData(
            Matchers.allOf(
                Matchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                Matchers.`is`("Japan")
            )
        ).perform(ViewActions.click())

        // check Index activity is showing up
        Espresso.onView(ViewMatchers.withId(R.id.index_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}