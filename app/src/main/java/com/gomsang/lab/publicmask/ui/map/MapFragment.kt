package com.gomsang.lab.publicmask.ui.map

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseFragment
import com.gomsang.lab.publicmask.databinding.FragmentMapBinding
import com.gomsang.lab.publicmask.libs.constants.Logger
import com.gomsang.lab.publicmask.libs.datas.Stock
import com.gomsang.lab.publicmask.libs.util.CoordinateUtil
import com.gomsang.lab.publicmask.ui.stock.StockDialog
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
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

    var markerList = mutableMapOf<Marker, Stock>()

    var lastQueriedLocation: com.gomsang.lab.publicmask.libs.datas.LatLng? = null

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
        viewModel.stockLiveData.observe(this, Observer {
            markerList.keys.forEach {
                it.map = null
            }
            markerList.clear()

            it.forEach {
                val infoWindow = InfoWindow()
                infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(context!!) {
                    override fun getText(infoWindow: InfoWindow): CharSequence {
                        return "잔여 " + it.remainCount
                    }
                }

                val marker = Marker()
                marker.position = LatLng(it.dealerLatitude!!, it.dealerLongitude!!)
                marker.map = map
                marker.onClickListener = object : Overlay.OnClickListener{
                    override fun onClick(p0: Overlay): Boolean {
                        val dialog = StockDialog.newInstance(it)
                        dialog.show(childFragmentManager, "dialog")
                        return true
                    }
                }

                if (it.isClosed) marker.iconTintColor = Color.RED

                infoWindow.open(marker)
                markerList.put(marker, it)


            }
        })
    }

    override fun initAfterBinding() {
    }

    override fun onMapReady(map: NaverMap) {
        map.minZoom = 14.5
        this.map = map

        map.uiSettings.isCompassEnabled = false
        map.uiSettings.isZoomControlEnabled = false

        // camera update
        val cameraUpdate =
            CameraUpdate.scrollTo(LatLng(place.latitude!!.toDouble(), place.longitude!!.toDouble()))
        map.moveCamera(cameraUpdate)

       /* // marker added
        val marker = Marker()
        marker.position = LatLng(place.latitude!!.toDouble(), place.longitude!!.toDouble())
        marker.map = map
*/
        map.addOnCameraChangeListener { reason, animated ->
            Log.i("NaverMap", "카메라 변경 - reson: $reason, animated: $animated")

            val target = map.cameraPosition.target
            if (lastQueriedLocation == null || CoordinateUtil.distFrom(
                    target.latitude,
                    target.longitude,
                    lastQueriedLocation!!.latitude,
                    lastQueriedLocation!!.longitude
                ) > 4000
            ) {
                viewModel.query(target.latitude, target.longitude)
                lastQueriedLocation = com.gomsang.lab.publicmask.libs.datas.LatLng().apply {
                    latitude = target.latitude
                    longitude = target.longitude
                }
            }
            if (lastQueriedLocation != null)
                Logger.log(
                    "cameraLog",
                    CoordinateUtil.distFrom(
                        target.latitude,
                        target.longitude,
                        lastQueriedLocation!!.latitude,
                        lastQueriedLocation!!.longitude
                    ).toString()
                )
        }

    }

}