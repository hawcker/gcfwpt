package com.gcfwpt.designonline.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * * 所有fragment的父类，在安卓中，fragment也是界面，和Activity一样，
 * 不同的是activity界面是整个屏幕窗口，而fragment是碎片化的窗口，也就是可以显示的范围大小可以自定义。
 * 两者都有生命周期，一般都是在Activity中嵌入一个或者多个fragment。
 * 本App中，HomeActivity中就嵌入了下面五个功能按钮，每个功能按钮点击就是切换到了不同的fragment
 * 比如点击我的首页，实际上就是点击了Activity中的控件，然后进入FragmentHome这个碎片化的窗口
 * fragment之间可以切换，所以点击不同按钮呈现不同碎片化的画面
 * 如不懂以下各个方法作用，需百度搜索fragment生命周期
 */
public class BaseFragment extends Fragment {

    protected Context mAllFragmentContext;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mAllFragmentContext == null){
            mAllFragmentContext = getActivity();
        }
        return null;
    }
}
