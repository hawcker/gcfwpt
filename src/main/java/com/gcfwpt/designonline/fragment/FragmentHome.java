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
 * 点击主页
 */
public class FragmentHome extends BaseFragment implements OnBackPressedListener {

    private static FragmentHome fragmentHome;
    private View view;

    public static FragmentHome newInstance(String key, String value) {
        if(fragmentHome == null){
            fragmentHome = new FragmentHome();
        }
        return fragmentHome;
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
        if(savedInstanceState!= null){
            String FRAGMENTS_TAG = "android:support:fragments";
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        try {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(fragmentHome != null){
            fragmentHome = null;
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