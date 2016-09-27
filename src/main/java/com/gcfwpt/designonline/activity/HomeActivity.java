package com.gcfwpt.designonline.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gcfwpt.designonline.MApplication;
import com.gcfwpt.designonline.R;
import com.gcfwpt.designonline.base.BaseActivity;
import com.gcfwpt.designonline.config.Constants;
import com.gcfwpt.designonline.fragment.FragmentDesigner;
import com.gcfwpt.designonline.fragment.FragmentHome;
import com.gcfwpt.designonline.fragment.FragmentMine;
import com.gcfwpt.designonline.fragment.FragmentTask;
import com.gcfwpt.designonline.listener.OnBackPressedListener;
import com.gcfwpt.designonline.utils.ExceptionUtil;



/**
 * 此类作用是加载首页的界面，包含五个下面的按钮，以及头部导航的初始化
 */
public class HomeActivity extends BaseActivity implements OnClickListener {
    private long startTime = 0;
    protected OnBackPressedListener onBackPressedListener;
    // 记录当前点击了哪个fragment
    public static int fragment_flag = 0;
    // Content View Elements

    private FrameLayout mFrame_content;
    private LinearLayout mMain_menu_linearlayout;
    private FrameLayout mMenu_layout_1;
    private ImageView mMenu_imageview_1;
    private TextView mMenu_textview_1;
    private FrameLayout mMenu_layout_2;
    private ImageView mMenu_imageview_2;
    private TextView mMenu_textview_2;
    private FrameLayout mMenu_layout_3;
    private ImageView mMenu_imageview_3;
    private TextView mMenu_textview_3;
    private FrameLayout mMenu_layout_4;
    private ImageView mMenu_imageview_4;
    private TextView mMenu_textview_4;

    // End Of Content View Elements

    private void bindViews() {

        mFrame_content = (FrameLayout) findViewById(R.id.frame_content);
        mMain_menu_linearlayout = (LinearLayout) findViewById(R.id.main_menu_linearlayout);
        mMenu_layout_1 = (FrameLayout) findViewById(R.id.menu_layout_1);
        mMenu_imageview_1 = (ImageView) findViewById(R.id.menu_imageview_1);
        mMenu_textview_1 = (TextView) findViewById(R.id.menu_textview_1);
        mMenu_layout_2 = (FrameLayout) findViewById(R.id.menu_layout_2);
        mMenu_imageview_2 = (ImageView) findViewById(R.id.menu_imageview_2);
        mMenu_textview_2 = (TextView) findViewById(R.id.menu_textview_2);
        mMenu_layout_3 = (FrameLayout) findViewById(R.id.menu_layout_3);
        mMenu_imageview_3 = (ImageView) findViewById(R.id.menu_imageview_3);
        mMenu_textview_3 = (TextView) findViewById(R.id.menu_textview_3);
        mMenu_layout_4 = (FrameLayout) findViewById(R.id.menu_layout_4);
        mMenu_imageview_4 = (ImageView) findViewById(R.id.menu_imageview_4);
        mMenu_textview_4 = (TextView) findViewById(R.id.menu_textview_4);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // 初始化头部
        try {
            //mTintManager.setStatusBarTintResource(R.drawable.nav_bg);
            bindViews();
            initEvents();
            initViews();
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    public void initEvents() {
        try {
            // 首页被点击
            mMenu_layout_1.setOnClickListener(this);
            mMenu_layout_2.setOnClickListener(this);
            mMenu_layout_3.setOnClickListener(this);
            mMenu_layout_4.setOnClickListener(this);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    private void initViews() {
        mMenu_textview_1.setTextSize(11);
        mMenu_textview_2.setTextSize(11);
        mMenu_textview_3.setTextSize(11);
        mMenu_textview_4.setTextSize(11);
    }

    // 点击抢订单
    public void clickHome() {
        try {
            fragment_flag = 1;
            FragmentManager manager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, FragmentHome.newInstance("title", Constants.FRAGMENTRUNSHORDERSTAG));
            // 事务管理提交
            fragmentTransaction.commit();
            // 改变选中状态
            mMenu_layout_1.setSelected(true);
            mMenu_imageview_1.setSelected(true);
            mMenu_textview_1.setSelected(true);

            mMenu_layout_2.setSelected(false);
            mMenu_imageview_2.setSelected(false);
            mMenu_textview_2.setSelected(false);

            mMenu_layout_3.setSelected(false);
            mMenu_imageview_3.setSelected(false);
            mMenu_textview_3.setSelected(false);

            mMenu_layout_4.setSelected(false);
            mMenu_imageview_4.setSelected(false);
            mMenu_textview_4.setSelected(false);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    /**
     * 点击任务大厅
     */
    public void clickTask() {
        try {
            fragment_flag = 2;
            FragmentManager manager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, FragmentTask.newInstance("title", Constants.FRAGMENTHANDLEORDERSTAG));
            fragmentTransaction.commit();

            mMenu_layout_1.setSelected(false);
            mMenu_imageview_1.setSelected(false);
            mMenu_textview_1.setSelected(false);

            mMenu_layout_2.setSelected(true);
            mMenu_imageview_2.setSelected(true);
            mMenu_textview_2.setSelected(true);

            mMenu_layout_3.setSelected(false);
            mMenu_imageview_3.setSelected(false);
            mMenu_textview_3.setSelected(false);

            mMenu_layout_4.setSelected(false);
            mMenu_imageview_4.setSelected(false);
            mMenu_textview_4.setSelected(false);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    /**
     * 点击设计师库
     */
    public void clickDesigner() {
        try {
            fragment_flag = 3;
            FragmentManager manager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, FragmentDesigner.newInstance("title", Constants.FRAGMENTMESSAGETAG));
            fragmentTransaction.commit();

            mMenu_layout_1.setSelected(false);
            mMenu_imageview_1.setSelected(false);
            mMenu_textview_1.setSelected(false);

            mMenu_layout_2.setSelected(false);
            mMenu_imageview_2.setSelected(false);
            mMenu_textview_2.setSelected(false);

            mMenu_layout_3.setSelected(true);
            mMenu_imageview_3.setSelected(true);
            mMenu_textview_3.setSelected(true);

            mMenu_layout_4.setSelected(false);
            mMenu_imageview_4.setSelected(false);
            mMenu_textview_4.setSelected(false);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    /**
     * 点击了“个人中心”按钮
     */
    public void clickMine() {
        try {
            fragment_flag = 4;
            FragmentManager manager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, FragmentMine.newInstance("title", Constants.FRAGMENTMINETAG));
            fragmentTransaction.addToBackStack(null);
            // 事务管理提交
            fragmentTransaction.commit();

            mMenu_layout_1.setSelected(false);
            mMenu_imageview_1.setSelected(false);
            mMenu_textview_1.setSelected(false);

            mMenu_layout_2.setSelected(false);
            mMenu_imageview_2.setSelected(false);
            mMenu_textview_2.setSelected(false);

            mMenu_layout_3.setSelected(false);
            mMenu_imageview_3.setSelected(false);
            mMenu_textview_3.setSelected(false);

            mMenu_layout_4.setSelected(true);
            mMenu_imageview_4.setSelected(true);
            mMenu_textview_4.setSelected(true);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        onBackPressedListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_layout_1:
                clickHome();
                break;
            case R.id.menu_layout_2:
                clickTask();
                break;
            case R.id.menu_layout_3:
                clickDesigner();
                break;
            case R.id.menu_layout_4:
                clickMine();
                break;
        }
    }


    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }


    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener.doBack();
        }else {
            Activity activity = MApplication.getInstance().getTopActivity();
            if (activity == HomeActivity.this) {
                if (System.currentTimeMillis() - startTime > 2000) {
                    Toast.makeText(HomeActivity.this, "再按一次退出", Toast.LENGTH_LONG).show();
                    startTime = System.currentTimeMillis();
                } else {
                    MApplication.getInstance().AppExit(getApplicationContext());
                }
            }
        }
    }

    public static void setFragmentFlag(int flag){
        fragment_flag = flag;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(fragment_flag == 0){
            fragment_flag = 1;
        }else{
            switch (fragment_flag){
                case 1:
                    clickHome();
                    break;
                case 2:
                    clickTask();
                    break;
                case 3:
                    clickDesigner();
                    break;
                case 4:
                    clickMine();
                    break;
            }
        }
    }
}

