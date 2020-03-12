package com.gomsang.lab.publicmask.ui.stock

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gomsang.lab.publicmask.databinding.DialogStockBinding
import com.gomsang.lab.publicmask.libs.datas.Stock
import com.gomsang.lab.publicmask.libs.util.TimeUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * 판매처 상세 정보 다이어로그
 */
class StockDialog : BottomSheetDialogFragment() {
    private var stock: Stock? = null
    private lateinit var binding: DialogStockBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stock = arguments!!.getParcelable(ARG_STOCK)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogStockBinding.inflate(
            inflater,
            container,
            false
        )
        binding.stock = stock


        stock!!.stockTime?.let {
            binding.stockTimeTextView.text = it
            TimeUtil.calcTimeDiff(it)?.let { diff ->
                TimeUtil.formatString(it)?.let { converted ->
                    binding.stockTimeTextView.text = String.format("%s (%s)", converted, diff)
                }
            }
        }

        stock!!.updatedTime?.let {
            binding.updatedTimeTextView.text = it
            TimeUtil.calcTimeDiff(it)?.let { diff ->
                TimeUtil.formatString(it)?.let { converted ->
                    binding.updatedTimeTextView.text = String.format("%s (%s)", converted, diff)
                }
            }
        }
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    companion object {
        const val ARG_STOCK = "arg_stock"
        fun newInstance(stock: Stock?): StockDialog {
            val bottomDownloadDialog = StockDialog()
            val args = Bundle()
            args.putParcelable(ARG_STOCK, stock)
            bottomDownloadDialog.arguments = args
            return bottomDownloadDialog
        }
    }
}