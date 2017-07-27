package com.lsh.recycleradapterdemo.act;

import android.view.LayoutInflater;
import android.view.View;

import com.lsh.recycleradapterdemo.R;

/**
 * Created by hua on 2016/6/30.
 */
public class SwipeLinearLayoutWithHeadAct extends SwipeLinearLayoutRefreshAct {
//    @Override
//    protected int getHeadViewLaout() {
//        return R.layout.include_head;
//    }

    @Override
    protected void addHeadView() {
        super.addHeadView();
        View view = LayoutInflater.from(this).inflate(R.layout.head2, mRvBaseRecycler, false);
        View view1 = LayoutInflater.from(this).inflate(R.layout.include_head, mRvBaseRecycler, false);
        try {
            mBaseAdapter.addHeadView(view);
            mBaseAdapter.addHeadView(view1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
