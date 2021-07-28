package op.mobile.project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import op.mobile.project.ui.activity.SplashScreenActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TestSplashScreenActivity {

    @Test
    fun testSplashActivity_inView() {
        val activityScenario = ActivityScenario.launch(SplashScreenActivity::class.java)

        // check entire activity is showing up
        onView(withId(R.id.splash_screen)).check(matches(isDisplayed()))

        // check lottie splash animation view is showing up
        onView(withId(R.id.splash_screen_animation_view)).check(
            matches(
                withEffectiveVisibility(
                    Visibility.VISIBLE
                )
            )
        )
    }

}