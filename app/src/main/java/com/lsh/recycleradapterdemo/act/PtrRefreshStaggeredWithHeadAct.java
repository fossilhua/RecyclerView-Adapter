package com.lsh.recycleradapterdemo.act;

import com.lsh.recycleradapterdemo.R;

/**
 * Created by hua on 2016/7/25.
 */
public class PtrRefreshStaggeredWithHeadAct extends PtrStaggeredRefreshAct {
    @Override
    protected int getHeadViewLaout() {
        return R.layout.include_head;
    }
}
