package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestMainActivity {

    @Test
    fun testMainActivity_inView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(ViewMatchers.withId(R.id.main_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // check basic view is showing up
        onView(ViewMatchers.withId(R.id.kia_ora))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.kia_ora)))

        onView(ViewMatchers.withId(R.id.question))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.which_country_do_you_want_to_travel)))

        onView(ViewMatchers.withId(R.id.spinner))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }

}