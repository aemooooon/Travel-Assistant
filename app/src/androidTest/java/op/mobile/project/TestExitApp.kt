package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestExitApp {

    @Test
    fun testQuizFragmentNavigation() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // click physic back button
        pressBack()

        // click physic back button again in 2 seconds after last click
        pressBack()

        // check the exit dialog fragment is showing up
        onView(withId(R.id.exit_fragment_dialog)).check(matches(isDisplayed()))

    }

}