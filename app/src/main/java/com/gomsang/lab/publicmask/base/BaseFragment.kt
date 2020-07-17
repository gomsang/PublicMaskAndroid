package com.gomsang.lab.publicmask.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gomsang.lab.publicmask.libs.util.ViewUtil

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel> : Fragment() {

    private var activity: BaseActivity<*, *>? = null

    lateinit var viewDataBinding: T

    var progressView: View? = null

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)

        initStartView()
        initDataBinding()
        initAfterBinding()
        return viewDataBinding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            activity = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    fun setProgressVisible(visible : Boolean){
        ViewUtil.disableEnableControls(!visible, viewDataBinding.root as ViewGroup)
        progressView?.let {
            it.isEnabled = visible
            it.visibility = if(visible) View.VISIBLE else View.INVISIBLE
        }
    }
}
