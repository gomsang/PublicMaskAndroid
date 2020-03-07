package com.gomsang.lab.publicmask.ui.map

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseFragment
import com.gomsang.lab.publicmask.databinding.FragmentMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import androidx.navigation.fragment.navArgs
import com.naver.maps.map.MapFragment
import com.naver.maps.map.overlay.Marker
import io.nlopez.smartlocation.SmartLocation
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback {
    override val layoutResourceId: Int
        get() = R.layout.fragment_map
    override val viewModel: MapViewModel by viewModel()

    private val args: MapFragmentArgs by navArgs()

    val place by lazy {
        args.place
    }

    var map: NaverMap? = null

    override fun initStartView() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)


        /*    SmartLocation.with(context).location()
                .oneFix()
                .start {
                    currentLocation.latitude
                    currentLocation.longitude

                }*/
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    override fun onMapReady(map: NaverMap) {
        this.map = map

        map.uiSettings.isCompassEnabled = false
        map.uiSettings.isZoomControlEnabled = false
        map.uiSettings.isZoomGesturesEnabled = false

        // camera update
        val cameraUpdate =
            CameraUpdate.scrollTo(LatLng(place.latitude!!.toDouble(), place.longitude!!.toDouble()))
        map.moveCamera(cameraUpdate)

        // marker added
        val marker = Marker()
        marker.position = LatLng(place.latitude!!.toDouble(), place.longitude!!.toDouble())
        marker.map = map

        map.addOnCameraChangeListener { reason, animated ->
            Log.i("NaverMap", "카메라 변경 - reson: $reason, animated: $animated")
        }
    }
}