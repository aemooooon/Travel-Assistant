/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import op.mobile.project.R
import op.mobile.project.model.City
import op.mobile.project.utils.CountryMarkerCluster
import op.mobile.project.repository.CountryRepository


// By default I set to New Zealand lat log here
var currentlatitude: Double = -40.900557
var currentlongitude: Double = 174.885971

class MapFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var map: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return

        val cities = CountryRepository(this).getCities()
        val countryLocation = CountryRepository(this).getCountry().countryLocation
        val cLocation = LatLng(countryLocation.countryLatitude, countryLocation.countryLongitude)

        val clusterManager: ClusterManager<City> = ClusterManager(activity, map)
        val markerCluster = map.let {
            activity?.let { it1 ->
                CountryMarkerCluster(
                    it1,
                    it,
                    clusterManager
                )
            }
        }
        clusterManager.renderer = markerCluster
        clusterManager.addItems(cities)
        clusterManager.cluster()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cLocation, 4f))

        setUpMap()

        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)

    }

    private fun setUpMap() {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        } else {
            map.isMyLocationEnabled = true
            map.uiSettings.isMyLocationButtonEnabled = true
            map.uiSettings.isZoomControlsEnabled = true
        }

    }

    override fun onMyLocationButtonClick(): Boolean {
        when {
            checkPermissions() -> map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(getMyLocation(), 12f)
            )
            else -> Toast.makeText(requireContext(), "Does not have Permissions", Toast.LENGTH_LONG)
                .show()
        }
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(activity, "Current location:\n$location", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingPermission")
    fun getMyLocation(): LatLng {
        val locationProvider: String = LocationManager.GPS_PROVIDER
        val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lastKnownLocation: Location? = locationManager.getLastKnownLocation(locationProvider)
        currentlatitude = lastKnownLocation!!.latitude
        currentlongitude = lastKnownLocation.longitude
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }

    private fun checkPermissions(): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this.requireContext(),
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }


}