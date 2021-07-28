/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import op.mobile.project.utils.Constants
import op.mobile.project.R
import op.mobile.project.ui.fragment.ExitDialogFragment

/**
 * Main Activity class which handle dropdown list to choose country
 *
 * @author Hua Wang
 */
class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    lateinit var backToast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countries: Array<String> = resources.getStringArray(R.array.countries)
        val spinner: Spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        spinner.adapter = adapter
        val intent = Intent(this, IndexActivity::class.java)

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
                if (countries[position] == "Select Country") {
//                    Toast.makeText(
//                        this@MainActivity,
//                        "Please select a country", Toast.LENGTH_SHORT
//                    ).show()
                } else {
                    Constants.COUNTRY_NAME = countries[position]
                    overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "No country selected", Toast.LENGTH_SHORT
                ).show()
            }

        }
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

}