/*
Project Name: Travel Assistant
Project Lecturer: Grayson Orr
Student Name: Hua Wang
*/
package op.mobile.project.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.ui.IconGenerator
import op.mobile.project.R
import op.mobile.project.databinding.ClusterMarkerBinding
import op.mobile.project.databinding.ClusterMarkerInfoWindowBinding
import op.mobile.project.model.City

/**
 * Google Map Country Marker and Cluster Operation
 */
class CountryMarkerCluster(
    context: Context,
    private val map: GoogleMap,
    clusterManager: ClusterManager<City>
) :
    DefaultClusterRenderer<City>(context, map, clusterManager),
    ClusterManager.OnClusterClickListener<City>,
    GoogleMap.OnInfoWindowClickListener {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val iconGen = IconGenerator(context)
    private var binding: ClusterMarkerBinding = DataBindingUtil.inflate(
        layoutInflater,
        R.layout.cluster_marker,
        null,
        false
    )

    init {
        val drawable: Drawable? = ContextCompat.getDrawable(context, android.R.color.transparent)
        iconGen.setBackground(drawable)
        iconGen.setContentView(binding.root)

        clusterManager.setOnClusterClickListener(this)
        clusterManager.markerCollection.setOnInfoWindowAdapter(InfoWindowAdapter())

        map.setInfoWindowAdapter(clusterManager.markerManager)
        map.setOnInfoWindowClickListener(this)
        map.setOnCameraIdleListener(clusterManager)
        map.setOnMarkerClickListener(clusterManager)
    }

    override fun onBeforeClusterItemRendered(item: City, markerOptions: MarkerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    }

    override fun onBeforeClusterRendered(
        cluster: Cluster<City>,
        markerOptions: MarkerOptions
    ) {
        "View more ${cluster.size} top rated views".also { binding.tvCluster.text = it }

        val icon: Bitmap = iconGen.makeIcon()
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon))
    }

    override fun onClusterItemRendered(clusterItem: City, marker: Marker) {
        marker.tag = clusterItem
    }

    override fun onClusterClick(cluster: Cluster<City>): Boolean {
        val builder = LatLngBounds.Builder()
        for (data: City in cluster.items)
            builder.include(data.position)

        try {
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 100))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return true
    }

    override fun onInfoWindowClick(marker: Marker) {}

    inner class InfoWindowAdapter : GoogleMap.InfoWindowAdapter {
        private val binding: ClusterMarkerInfoWindowBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.cluster_marker_info_window,
            null,
            false
        )

        override fun getInfoWindow(marker: Marker): View {
            val city: City = marker.tag as City
            binding.tvName.text = city.cityName
            """Lat: ${city.cityLocation.cityLatitude}, Lng: ${city.cityLocation.cityLongitude}""".also { binding.tvCity.text = it }
            return binding.root
        }

        override fun getInfoContents(marker: Marker): View? {
            return null
        }
    }
}