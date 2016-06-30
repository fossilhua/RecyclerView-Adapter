package com.lsh.recycleradapterdemo.act;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hua on 2016/6/30.
 */
public class PtrLinearLayoutRefreshAct extends BasePtrRefreshAct{
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
