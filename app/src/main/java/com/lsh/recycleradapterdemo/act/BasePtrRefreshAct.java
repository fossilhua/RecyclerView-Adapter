package com.lsh.recycleradapterdemo.act;

import android.view.View;

import com.lsh.recycleradapterdemo.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public abstract class BasePtrRefreshAct extends BaseRefreshAct {
    private PtrClassicFrameLayout ptrFrame;

    @Override
    protected void initRefreshLayout() {
        ptrFrame = (PtrClassicFrameLayout) findViewById(R.id.store_house_ptr_frame);
        ptrFrame.disableWhenHorizontalMove(true);
        ptrFrame.setLastUpdateTimeRelateObject(this);
        pullDownRefresh();
        firstRefresh();
    }

    @Override
    protected void pullDownRefresh() {
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullDownData();
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return !isLoadingMore() && PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    @Override
    protected void firstRefresh() {
        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.autoRefresh();
            }
        }, 100);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_ptr_refresh;
    }

    @Override
    protected boolean isPullDown() {
        return ptrFrame.isRefreshing();
    }

    @Override
    protected void pullDownComplete() {
        ptrFrame.refreshComplete();
    }

}
