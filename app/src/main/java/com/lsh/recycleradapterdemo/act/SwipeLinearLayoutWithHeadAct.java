package com.lsh.recycleradapterdemo.act;

import com.lsh.recycleradapterdemo.R;

/**
 * Created by hua on 2016/6/30.
 */
public class SwipeLinearLayoutWithHeadAct extends SwipeLinearLayoutRefreshAct {
    @Override
    protected int getHeadViewLaout() {
        return R.layout.include_head;
    }
}
