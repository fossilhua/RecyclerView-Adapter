package com.lsh.recycleradapterdemo.act;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 *
 * Created by hua on 2016/6/30.
 */
public class PtrStaggeredRefreshAct extends BasePtrRefreshAct{
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
    }
}
