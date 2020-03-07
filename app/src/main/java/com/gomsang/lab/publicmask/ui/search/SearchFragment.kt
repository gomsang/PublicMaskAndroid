package com.gomsang.lab.publicmask.ui.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomsang.lab.publicmask.R
import com.gomsang.lab.publicmask.base.BaseFragment
import com.gomsang.lab.publicmask.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModel()

    override fun initStartView() {
        val searchResultAdapter = SearchResultAdapter(context!!)
        viewDataBinding.resultRecycler.adapter = searchResultAdapter
        viewDataBinding.resultRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.messageLiveData.observe(this, Observer {
            it?.let {
                if (it is String) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.messageLiveData.value = null
            }
        })
        viewModel.placeSearchPlacesLiveData.observe(this, Observer {
            it?.let {

                viewDataBinding.controlArea.visibility = View.GONE
                viewDataBinding.resultRecycler.visibility = View.VISIBLE
                searchResultAdapter.replace(it)
                searchResultAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        viewDataBinding.keywordEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(viewDataBinding.keywordEditText.text.toString())

                val imm: InputMethodManager? = context!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
                imm?.hideSoftInputFromWindow(viewDataBinding.keywordEditText.windowToken, 0)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}