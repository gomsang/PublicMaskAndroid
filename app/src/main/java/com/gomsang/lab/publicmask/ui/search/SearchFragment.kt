package com.gomsang.lab.publicmask.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseFragment
import com.gomsang.lab.publicmask.databinding.FragmentSearchBinding
import com.gomsang.lab.publicmask.libs.OnRecyclerItemClickListener
import com.gomsang.lab.publicmask.libs.datas.Place
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import io.nlopez.smartlocation.SmartLocation
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModel()

    override fun initStartView() {
        val searchResultAdapter = SearchResultAdapter(context!!)
        viewDataBinding.resultRecycler.adapter = searchResultAdapter
        viewDataBinding.resultRecycler.layoutManager = LinearLayoutManager(context)
        searchResultAdapter.onRecyclerItemClickListener =
            object : OnRecyclerItemClickListener<Place> {
                override fun onClicked(position: Int, data: Place) {
                    val nav = SearchFragmentDirections.actionSearchFragmentToMapFragment(data)
                    findNavController().navigate(nav)
                }
            }

        viewModel.messageLiveData.observe(this, Observer {
            it?.let {
                if (it is String) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.messageLiveData.value = null
            }
        })
        viewModel.placeSearchPlacesLiveData.observe(this, Observer {
            it?.let {
                if (it.size > 0) layoutSwitch(true)
                searchResultAdapter.replace(it)
                searchResultAdapter.notifyDataSetChanged()
            }
        })

    }

    override fun initDataBinding() {
    }

    private fun layoutSwitch(searched: Boolean) {
        viewDataBinding.controlArea.visibility = if (searched) View.GONE else View.VISIBLE
        viewDataBinding.resultRecycler.visibility = if (searched) View.VISIBLE else View.GONE
    }

    override fun initAfterBinding() {
        viewDataBinding.findNearbyBtn.setOnClickListener {
            Toast.makeText(context, "사용자 위치를 가져오고 있습니다. 잠시만 기다려주세요.", Toast.LENGTH_SHORT).show()
            SmartLocation.with(context).location()
                .oneFix()
                .start {
                    val place = Place().apply {
                        latitude = it.latitude.toString()
                        longitude = it.longitude.toString()
                    }
                    val nav = SearchFragmentDirections.actionSearchFragmentToMapFragment(place)
                    findNavController().navigate(nav)
                }
        }

        viewDataBinding.keywordEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(viewDataBinding.keywordEditText.text.toString())

                val imm: InputMethodManager? =
                    context!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(viewDataBinding.keywordEditText.windowToken, 0)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        viewDataBinding.keywordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!viewModel.latestSearchKeyword.equals(s.toString())) {
                    layoutSwitch(false)
                }
            }
        })
    }
}