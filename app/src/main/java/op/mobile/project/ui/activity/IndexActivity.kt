/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.activity

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import op.mobile.project.R
import op.mobile.project.ui.fragment.ExitDialogFragment
import op.mobile.project.utils.Constants

/**
 * Activity which main application container page
 *
 * @author Hua Wang
 */
class IndexActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private var isDark: Boolean = false
    private var countryName: String? = null
    private var backPressedTime: Long = 0
    lateinit var backToast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        // share preferences
        sharedPref = this.getPreferences(Context.MODE_PRIVATE)!!

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        // get origin value
        isDark = sharedPref.getBoolean(getString(R.string.dark), false)
        isDark = isDark == true

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // This configuration only set to change Action Bar title
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_translator,
                R.id.navigation_text_to_speech,
                R.id.navigation_phrases,
                R.id.navigation_quiz,
                R.id.navigation_map
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    // option menu inflate
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    // option value selected event listener
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_country -> {
                val mBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
                val mView: View = layoutInflater.inflate(R.layout.dialog_spinner, null)
                mBuilder.setTitle("Switch country")
                val countries: Array<String> = resources.getStringArray(R.array.countries)
                val dialogSpinner: Spinner = mView.findViewById(R.id.dialog_spinner)
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
                dialogSpinner.adapter = adapter
                mBuilder.setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener(function = { _: DialogInterface, _: Int ->
                        if (dialogSpinner.selectedItem.toString() == "Select Country") {
                            Toast.makeText(
                                this,
                                "Please select a country", Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Constants.COUNTRY_NAME = dialogSpinner.selectedItem.toString()

                            // reload current activity once the country has been changed
                            finish()
                            startActivity(intent)
                        }
                    })
                )
                mBuilder.setNegativeButton(
                    "Cancel",
                    DialogInterface.OnClickListener() { _: DialogInterface, _: Int ->
                        fun onClick(dialogInterface: DialogInterface) {
                            dialogInterface.dismiss()
                        }
                    }
                )
                mBuilder.setView(mView)
                val dialog: AlertDialog = mBuilder.create()
                dialog.show()
            }
            R.id.change_theme -> {
                isDark = isDark == false
                sharedPref.edit().putBoolean("dark", isDark).apply()
                if (sharedPref.getBoolean("dark", true)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    delegate.applyDayNight()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    delegate.applyDayNight()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    // check whether double back press and start exit dialog
    override fun onBackPressed() {
        backToast = Toast.makeText(this, "Press back again to exit the app.", Toast.LENGTH_SHORT)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            val dialog = ExitDialogFragment()
            dialog.show(supportFragmentManager, "exit_dialog")
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    // enable back button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}