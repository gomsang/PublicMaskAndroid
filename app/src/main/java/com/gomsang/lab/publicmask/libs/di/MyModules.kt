package com.gomsang.lab.publicmask.libs.di

import com.gomsang.lab.publicmask.models.MaskRepository
import com.gomsang.lab.publicmask.models.MaskRepositoryImpl
import com.gomsang.lab.publicmask.models.PlaceRepository
import com.gomsang.lab.publicmask.models.PlaceRepositoryImpl
import com.gomsang.lab.publicmask.ui.main.MainViewModel
import com.gomsang.lab.publicmask.ui.map.MapViewModel
import com.gomsang.lab.publicmask.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

var viewmodels = module {
    viewModel { MapViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { MainViewModel() }
}

var models = module {
    single<PlaceRepository> { PlaceRepositoryImpl() }
    single<MaskRepository> { MaskRepositoryImpl() }
}

var myDiModule = listOf(
    viewmodels,
    models
)