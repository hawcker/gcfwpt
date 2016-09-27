package com.gcfwpt.designonline.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcfwpt.designonline.R;
import com.gcfwpt.designonline.activity.HomeActivity;
import com.gcfwpt.designonline.base.BaseFragment;
import com.gcfwpt.designonline.listener.OnBackPressedListener;
import com.gcfwpt.designonline.utils.ExceptionUtil;

/**
 * 个人中心碎片化界面
 */
public class FragmentMine extends BaseFragment implements OnBackPressedListener {

    private static FragmentMine fragmentMine;
    private View view;

    public static FragmentMine newInstance(String key, String value) {
        if(fragmentMine == null){
            fragmentMine = new FragmentMine();
        }
        return fragmentMine;
    }


    // 避免getactivity空指针异常 不再保存Fragment的状态 让fragment随MyActivity一起销毁
    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // 避免getactivity空指针异常 不再保存Fragment的状态 让fragment随MyActivity一起销毁 清除已经保存的fragment的状态
        if (savedInstanceState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        try {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(fragmentMine != null){
            fragmentMine = null;
        }
    }


    @Override
    public void doBack() {
        try {
            // 当前fragment情况下按返回键
            ((HomeActivity) mAllFragmentContext).setOnBackPressedListener(null);
            ((HomeActivity) mAllFragmentContext).onBackPressed();
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        try {
            super.onViewCreated(view, savedInstanceState);
            ((HomeActivity) mAllFragmentContext).setOnBackPressedListener(this);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

}
