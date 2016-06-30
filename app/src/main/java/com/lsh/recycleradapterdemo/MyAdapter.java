package com.lsh.recycleradapterdemo;

import android.content.Context;
import android.widget.TextView;

import com.lsh.lshrecyclerviewadapter.BaseAdapter;
import com.lsh.lshrecyclerviewadapter.BaseViewHolder;

import java.util.List;

/**
 * Created by hua on 2016/6/23.
 */
public class MyAdapter extends BaseAdapter<DataBean> {

    public MyAdapter(Context mContext, List<DataBean> list) {
        super(mContext, list, R.layout.item_recy);
    }

    @Override
    public  void onBindData(BaseViewHolder holder, List<DataBean> data, int position) {
        DataBean dataBean = data.get(position);
        TextView tv = holder.retrieveView(R.id.textView);
        tv.setText(dataBean.getDes());
    }
}
