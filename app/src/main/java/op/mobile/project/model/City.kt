/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Data entity class of City
 *
 * @author Hua Wang
 */
data class City(
    val cityName: String,
    val cityLocation: CityLocation
) : ClusterItem {

    data class CityLocation(
        val cityLatitude: Double,
        val cityLongitude: Double
    )

    override fun getPosition(): LatLng = LatLng(cityLocation.cityLatitude, cityLocation.cityLongitude)
    override fun getTitle(): String = cityName
    override fun getSnippet(): String = ""

}
