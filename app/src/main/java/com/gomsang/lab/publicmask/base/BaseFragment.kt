package com.gomsang.lab.publicmask.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.gomsang.lab.publicmask.libs.ViewUtil

abstract class BaseFragment<T : ViewDataBinding, R : BaseViewModel> : Fragment() {

    private var activity: BaseActivity<*, *>? = null

    lateinit var viewDataBinding: T

    var progressView: View? = null

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    /**
     * 레이아웃을 띄운 직후 호출.
     * 뷰나 액티비티의 속성 등을 초기화.
     * ex) 리사이클러뷰, 툴바, 드로어뷰..
     */
    abstract fun initStartView()

    /**
     * 두번째로 호출.
     * 데이터 바인딩 및 rxjava 설정.
     * ex) rxjava observe, databinding observe..
     */
    abstract fun initDataBinding()

    /**
     * 바인딩 이후에 할 일을 여기에 구현.
     * 그 외에 설정할 것이 있으면 이곳에서 설정.
     * 클릭 리스너도 이곳에서 설정.
     */
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
