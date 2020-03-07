package com.gomsang.lab.publicmask.ui.stock;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gomsang.lab.publicmask.databinding.DialogStockBinding;
import com.gomsang.lab.publicmask.libs.datas.Stock;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * 판매처 상세 정보 다이어로그
 */
public class StockDialog extends BottomSheetDialogFragment {

    static final String ARG_STOCK = "arg_stock";

    private Stock stock;
    private DialogStockBinding binding;

    public static StockDialog newInstance(Stock stock) {
        StockDialog bottomDownloadDialog = new StockDialog();
        Bundle args = new Bundle();
        args.putParcelable(ARG_STOCK, stock);
        bottomDownloadDialog.setArguments(args);
        return bottomDownloadDialog;
    }

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stock = getArguments().getParcelable(ARG_STOCK);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DialogStockBinding.inflate(inflater, container, false);
        binding.setStock(stock);


        return binding.getRoot();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}