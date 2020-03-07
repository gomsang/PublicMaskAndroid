package com.gomsang.lab.publicmask.ui.search

import android.widget.SearchView
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseFragment
import com.gomsang.lab.publicmask.databinding.FragmentSearchBinding
import com.gomsang.lab.publicmask.libs.di.viewmodels
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModel()

    override fun initStartView() {
        viewModel.search("웅천지웰")
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}