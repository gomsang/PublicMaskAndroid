package com.gomsang.lab.publicmask.ui.map

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseFragment
import com.gomsang.lab.publicmask.databinding.FragmentMapBinding
import com.gomsang.lab.publicmask.libs.constants.Logger
import com.gomsang.lab.publicmask.libs.datas.Stock
import com.gomsang.lab.publicmask.libs.util.CoordinateUtil
import com.gomsang.lab.publicmask.libs.util.StatUtil
import com.gomsang.lab.publicmask.ui.stock.StockDialog
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.MarkerIcons
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * 지도를 나타내는 프래그먼트
 */
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback {
    override val layoutResourceId: Int
        get() = R.layout.fragment_map
    override val viewModel: MapViewModel by viewModel()

    private val args: MapFragmentArgs by navArgs()

    val place by lazy {
        args.place
    }

    var map: NaverMap? = null

    private var markerList = mutableMapOf<Marker, Stock>()

    private var lastQueriedLocation: com.gomsang.lab.publicmask.libs.datas.LatLng? = null

    override fun initStartView() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    override fun initDataBinding() {
        viewModel.stockLiveData.observe(this, Observer { stocks ->

            // when new stock data applied, remove all marker from map
            markerList.keys.forEach {
                it.map = null
            }
            markerList.clear()

            stocks.forEach { stock ->
                if (stock.dealerLatitude == null || stock.dealerLongitude == null) return@forEach
                val infoWindow = InfoWindow()
                infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(context!!) {
                    override fun getText(infoWindow: InfoWindow): CharSequence {
                        return StatUtil.convertStatToString(stock.remainStat!!)
                    }
                }

                val marker = Marker()
                marker.position = LatLng(stock.dealerLatitude!!, stock.dealerLongitude!!)
                marker.onClickListener = Overlay.OnClickListener {
                    val dialog = StockDialog.newInstance(stock)
                    dialog.show(childFragmentManager, "dialog")
                    true
                }

                when (stock.remainStat) {
                    "plenty" -> marker.icon = MarkerIcons.GREEN
                    "some" -> marker.icon = MarkerIcons.YELLOW
                    "few" ->
                        marker.icon = MarkerIcons.RED
                    "empty" ->
                        marker.icon = MarkerIcons.GRAY
                    else -> {
                        marker.icon = MarkerIcons.GRAY
                    }
                }
                marker.map = map
                infoWindow.open(marker)

                markerList[marker] = stock
            }
        })
    }

    override fun initAfterBinding() {
        viewDataBinding.openPageButton.setOnClickListener {
            val uri: Uri = Uri.parse("https://blog.naver.com/kfdazzang/221839489769")
            val it = Intent(Intent.ACTION_VIEW, uri)
            startActivity(it)
        }
    }

    override fun onMapReady(map: NaverMap) {
        // limit min zoom for delay api requesting.
        map.minZoom = 15.0
        this.map = map

        map.uiSettings.isCompassEnabled = false

        // camera update to selected location
        val cameraUpdate =
            CameraUpdate.scrollTo(LatLng(place.latitude!!.toDouble(), place.longitude!!.toDouble()))
        map.moveCamera(cameraUpdate)

        map.addOnCameraChangeListener { _, _ ->
            val target = map.cameraPosition.target
            Logger.log("CAMERA_CHANGED", String.format("%f, %f", target.latitude, target.longitude))
            // if last queried location is not exist or distance from last queried location is more than 4km, query approved.
            if (lastQueriedLocation == null || CoordinateUtil.distFrom(
                    target.latitude,
                    target.longitude,
                    lastQueriedLocation!!.latitude,
                    lastQueriedLocation!!.longitude
                ) > 2700
            ) {
                viewModel.query(target.latitude, target.longitude)
                lastQueriedLocation = com.gomsang.lab.publicmask.libs.datas.LatLng().apply {
                    latitude = target.latitude
                    longitude = target.longitude
                }
            }
        }
    }

}