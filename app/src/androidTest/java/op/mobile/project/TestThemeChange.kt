package op.mobile.project

import android.content.Context
import android.content.SharedPreferences
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
class TestThemeChange {

    @Test
    fun testThemeChange() {
        val activityScenario = ActivityScenario.launch(IndexActivity::class.java)

        // click dark toggle icon locate in action bar
        onView(withId(R.id.change_theme)).perform(click())

        // check the view is still showing up
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))

    }

}