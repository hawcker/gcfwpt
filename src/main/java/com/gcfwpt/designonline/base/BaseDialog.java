package com.gcfwpt.designonline.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;

import com.gcfwpt.designonline.R;


/**
 * 作者：moke
 * 创建时间：2015/10/27 18:24
 * 描述：
 */
public  class BaseDialog extends Dialog implements View.OnClickListener {
    public BaseDialog(Context context) {
        super(context, R.style.DialogTheme);
    }
    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }
    @Override
    public void onClick(View v) {

    }
    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }
}