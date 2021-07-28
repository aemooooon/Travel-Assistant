/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.repository

import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import op.mobile.project.utils.Constants
import op.mobile.project.R
import op.mobile.project.model.City
import op.mobile.project.model.Country
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Data Repository class of Country
 *
 * @author Hua Wang
 */
class CountryRepository(private val context: Fragment) {

    // read json by gson
    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.map)

    private val itemType = object : TypeToken<List<Country>>() {}.type
    private val reader = InputStreamReader(inputStream)

    // get country info by country name
    private fun fetchCountries(): List<Country> {
        return Gson().fromJson<List<Country>>(reader, itemType)
            .filter {
                it.countryName == Constants.COUNTRY_NAME
            }
    }

    // get country
    fun getCountry(): Country {
        return fetchCountries()[0]
    }

    // get city list
    fun getCities(): List<City> {
        return fetchCountries().map { it }[0].cities
    }

}