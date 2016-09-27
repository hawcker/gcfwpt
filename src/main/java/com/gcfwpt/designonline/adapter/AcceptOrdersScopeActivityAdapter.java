//package com.gcfwpt.designonline.adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.gcfwpt.designonline.R;
//import com.gcfwpt.designonline.entity.AcceptTypeServices;
//import com.gcfwpt.designonline.utils.ExceptionUtil;
//import com.gcfwpt.designonline.utils.ViewHolder;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2016/8/2.
// */
//public class AcceptOrdersScopeActivityAdapter extends BaseAdapter {
//    private Context mContext = null;
//    private List<AcceptTypeServices> mAcceptTypeServicesList;
//    private LayoutInflater mLayoutInflater;
//
//    public AcceptOrdersScopeActivityAdapter(Context context, List<AcceptTypeServices> mAcceptTypeServicesList) {
//        this.mContext = context;
//        this.mAcceptTypeServicesList = mAcceptTypeServicesList;
//        if (mLayoutInflater == null) {
//            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return mAcceptTypeServicesList != null ? mAcceptTypeServicesList.size() : 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mAcceptTypeServicesList != null ? mAcceptTypeServicesList.get(position) : null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        try {
//            if (convertView == null) {
//                convertView = mLayoutInflater.inflate(R.layout.activity_accept_orders_scope_adapter, parent,false);
//            }
//
//            TextView activity_accept_orders_scope_adapter_textview = ViewHolder.get(convertView, R.id.activity_accept_orders_scope_adapter_textview);
//            activity_accept_orders_scope_adapter_textview.setText("ds");
//        } catch (Exception e) {
//            ExceptionUtil.handleException(e);
//        }
//        return convertView;
//    }
//}
