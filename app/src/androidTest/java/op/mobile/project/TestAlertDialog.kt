package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.IndexActivity
import op.mobile.project.ui.activity.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestAlertDialog {

    @Test
    fun testDialog() {
        val activityScenario = ActivityScenario.launch(IndexActivity::class.java)

        // click location icon locate in action bar
        onView(withId(R.id.change_country)).perform(click())

        // check alert dialog is showing up
        onView(withText(R.string.switch_country)).check(matches(isDisplayed()))

        // check spinner inside of alert dialog is working
        onView(withId(R.id.dialog_spinner)).perform(click())

    }

}