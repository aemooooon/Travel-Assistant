package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.MainActivity
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestFragmentNavigation {

    @Test
    fun testQuizFragmentNavigation() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // declare Action bar back button
        val actionBarBackButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                isDisplayed()
            )
        )

        // choose country
        onView(withId(R.id.spinner)).perform(click())

        // select New Zealand from spinner
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("New Zealand"))).perform(click())

        // click New zealand option
        onView(withId(R.id.navigation_quiz)).perform(click())

        // click start quiz button
        onView(withId(R.id.btn_start)).perform(click())

        // Check the question fragment is showing up
        onView(withId(R.id.fragment_question)).check(matches(isDisplayed()))

        // click back button which on action bar
        actionBarBackButton.perform(click())

        // check the quiz fragment is showing up
        onView(withId(R.id.fragment_quiz)).check(matches(isDisplayed()))

    }

}