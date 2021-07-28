/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.model

/**
 * Data entity class of Country
 *
 * @author Hua Wang
 */
data class Country
    (
    val countryName: String,
    val countryLocation: CountryLocation,
    val cities: List<City>,
) {
    data class CountryLocation(
        val countryLatitude: Double,
        val countryLongitude: Double
    )
}
