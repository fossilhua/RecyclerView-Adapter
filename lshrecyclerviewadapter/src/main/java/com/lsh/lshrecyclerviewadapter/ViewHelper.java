package com.lsh.lshrecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 123 on 2017/7/13 09:58.
 */

public class ViewHelper {


    public View initStatusView(Context context, View statusView, View headView, RecyclerView view) {
        int height = CommUtils.convert(context,50);
        if (headView == null) {
            statusView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else if (headView.getHeight() + height < view.getHeight()) {
            statusView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, view.getHeight() - headView.getHeight()));
        } else {
            statusView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        }
        return statusView;
    }
}
