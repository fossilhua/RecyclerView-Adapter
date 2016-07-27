package com.lsh.lshrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by hua on 2016/6/23.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public SparseArray<View> viewList = new SparseArray<>();

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public <T extends View> T retrieveView(int viewId) {
        View view = viewList.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewList.put(viewId, view);
        }
        return (T) view;
    }

}
