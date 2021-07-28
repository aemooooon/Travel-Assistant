/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import op.mobile.project.R

/**
 * Splash Screen Activity class
 *
 * @author Hua Wang
 */
class SplashScreenActivity : AppCompatActivity() {
    // Splash animation showing up time
    private var delayTime: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // hide the action bar
        supportActionBar?.hide()

        val intent = Intent(this, MainActivity::class.java)
        jumpToIntent(intent) // Jump to Main activity after show time
    }

    /**
     * Jump to Main Activity
     */
    private fun jumpToIntent(intent: Intent) {
        Handler(Looper.getMainLooper()).postDelayed({
            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
            startActivity(intent)
            finish()

        }, delayTime)
    }
}