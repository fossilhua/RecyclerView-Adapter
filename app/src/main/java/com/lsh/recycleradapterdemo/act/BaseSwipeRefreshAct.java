package com.lsh.recycleradapterdemo.act;

import android.support.v4.widget.SwipeRefreshLayout;

import com.lsh.recycleradapterdemo.R;

public abstract class BaseSwipeRefreshAct extends BaseRefreshAct {
    private SwipeRefreshLayout mSwip;


    @Override
    protected void initRefreshLayout() {
        mSwip = (SwipeRefreshLayout) findViewById(R.id.swip);
    }

    @Override
    protected void firstRefresh() {
        mSwip.post(new Runnable() {

            @Override
            public void run() {
                mSwip.setRefreshing(true);
            }
        });
        initData();
    }

    @Override
    protected void pullDownRefresh() {
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullDownData();
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_swipe_refresh;
    }

    @Override
    protected boolean isPullDown() {
        return mSwip.isRefreshing();
    }

    @Override
    protected void pullDownComplete() {
        mSwip.setRefreshing(false);
    }
}
