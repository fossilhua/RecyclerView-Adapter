package com.lsh.lshrecyclerviewadapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hua on 2016/6/23.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 2;
    boolean isBottomEnable = false;//是否显示底部loadmore
    boolean isHeadEnable = false;//是否添加了headview
    boolean isLoadingMore = false;//是否正在加载

    private List<T> mDataList;
    protected Context mContext;
    private int itemLayout;

    private String bottomDes = "加载更多";
    private BottomHolder bottomHolder;

    public BaseAdapter(Context mContext, List<T> list, int itemLayout) {
        this.mDataList = list;
        this.mContext = mContext;
        this.itemLayout = itemLayout;
    }

    public abstract void onBindData(BaseViewHolder holder, List<T> data, int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeadHolder(headView);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_foot, parent, false);
            return new BottomHolder(view);
        } else {
            View itemView = LayoutInflater.from(mContext).inflate(itemLayout, parent, false);

            return new BaseViewHolder(itemView);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int pos = holder.getLayoutPosition();
        int type = getItemViewType(pos);
        if (type == TYPE_HEADER || type == TYPE_FOOTER) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                p.setFullSpan(true);
            }
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) {
            //do nothing

        } else if (viewType == TYPE_FOOTER) {
            bottomHolder = (BottomHolder) holder;
            bottomHolder.bottomText.setText(bottomDes);
        } else {
            int pos = holder.getAdapterPosition();
            if (isHeadEnable) {
                pos -= 1;
            }
            final int finalPos = pos;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(finalPos);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mOnItemLongClickListener != null) {
                        return mOnItemLongClickListener.onItemLongClick(finalPos);
                    }
                    return false;
                }
            });
            onBindData((BaseViewHolder) holder, mDataList, pos);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isBottomEnable && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else if (isHeadEnable && position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_CONTENT;
    }

    @Override
    public int getItemCount() {
        int totalSize = mDataList.size();
        if (isHeadEnable) {
            totalSize = totalSize + 1;
        }
        if (isBottomEnable) {
            totalSize = totalSize + 1;
        }
        return totalSize;
    }


    ///////////////////////////////////////////////////////////////////////////
// 扩展部分
///////////////////////////////////////////////////////////////////////////
    //是否显示progressbar 加载更多的时候显示，结束的时候隐藏
    private void showBottomProgressBar() {
        if (bottomHolder != null) {
            bottomHolder.progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideBottomProgressBar() {
        if (bottomHolder != null) {
            bottomHolder.progressBar.setVisibility(View.GONE);
        }
    }

    //header 请在setadapter()前addView  否则无效
    View headView;

    public void addHeadView(View headView) {
        this.headView = headView;
        isHeadEnable = true;
        notifyDataSetChanged();
    }


    class HeadHolder extends RecyclerView.ViewHolder {

        public HeadHolder(View itemView) {
            super(itemView);
        }
    }

    //footer
    class BottomHolder extends RecyclerView.ViewHolder {
        private TextView bottomText;
        private ProgressBar progressBar;

        public BottomHolder(View itemView) {
            super(itemView);
            bottomText = (TextView) itemView.findViewById(R.id.tv_item_bottom);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pb_item_loading);
        }
    }


    public void setBottomEnable(boolean bln) {
        isBottomEnable = bln;
        notifyDataSetChanged();
    }

    ///////////////////////////////////////////////////////////////////////////
// itemclick
///////////////////////////////////////////////////////////////////////////
    OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    //
    OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int pos);
    }
    ///////////////////////////////////////////////////////////////////////////
// 加载更多
///////////////////////////////////////////////////////////////////////////

    public boolean isLoadingMore() {
        return isLoadingMore;
    }


    public void setOnLoadMoreListener(RecyclerView recyclerView, final OnLoadMoreListener onLoadMoreListener) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //SCROLL_STATE_IDLE  闲置的 SCROLL_STATE_DRAGGING 拖曳的 SCROLL_STATE_SETTLING 固定的
                if (!isLoadingMore && newState == RecyclerView.SCROLL_STATE_IDLE && isScollBottom(recyclerView)) {
                    if (onLoadMoreListener.isCanLoadMore()) {
                        isLoadingMore = true;
                        showBottomProgressBar();
                        onLoadMoreListener.onLoadMore();
                    }

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    /**
     * 结束加载更多
     */
    public void compeleteLoadmore() {
        isLoadingMore = false;
        //隐藏loading
        hideBottomProgressBar();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();

        boolean isCanLoadMore();
    }


    private boolean isScollBottom(RecyclerView recyclerView) {
        return !isCanScollVertically(recyclerView);
    }

    private boolean isCanScollVertically(RecyclerView recyclerView) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            return ViewCompat.canScrollVertically(recyclerView, 1) || recyclerView.getScrollY() < recyclerView.getHeight();
        } else {
            return ViewCompat.canScrollVertically(recyclerView, 1);
        }
    }

}
