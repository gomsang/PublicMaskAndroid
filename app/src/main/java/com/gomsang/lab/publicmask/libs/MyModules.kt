package com.gomsang.lab.publicmask.libs

import com.gomsang.lab.publicmask.ui.map.MapViewModel
import com.gomsang.lab.publicmask.ui.search.SearchViewModel
import com.naver.maps.map.MapView
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

var viewmodels = module {
    viewModel { MapViewModel() }
    viewModel { SearchViewModel() }
}

var myDiModule = listOf(viewmodels)