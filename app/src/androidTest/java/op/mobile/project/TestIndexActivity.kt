package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.IndexActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestIndexActivity {

    @Test
    fun testSplashActivity_inView() {
        val activityScenario = ActivityScenario.launch(IndexActivity::class.java)

        // check the nav menu is showing up
        onView(withId(R.id.nav_view))
            .check(matches(isDisplayed()))
    }

}