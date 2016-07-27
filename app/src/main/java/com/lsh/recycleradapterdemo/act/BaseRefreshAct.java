package com.lsh.recycleradapterdemo.act;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.lsh.lshrecyclerviewadapter.BaseAdapter;
import com.lsh.recycleradapterdemo.DataBean;
import com.lsh.recycleradapterdemo.MyAdapter;
import com.lsh.recycleradapterdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hua on 2016/6/30.
 */
public abstract class BaseRefreshAct extends AppCompatActivity {
    private RecyclerView mRvBaseRecycler;
    private List<DataBean> mDataList = new ArrayList<>();
    public MyAdapter mBaseAdapter;
    int page = 1;

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract void initRefreshLayout();

    protected abstract void firstRefresh();

    protected abstract void pullDownRefresh();

    //默认不显示head
    protected int getHeadViewLaout() {
        return 0;
    }

    protected abstract int getLayout();

    protected abstract boolean isPullDown();//是否下拉刷新中


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initRefreshLayout();
        mRvBaseRecycler = (RecyclerView) findViewById(R.id.rv_base_recycler);
        mRvBaseRecycler.setLayoutManager(getLayoutManager());
        mBaseAdapter = new MyAdapter(BaseRefreshAct.this, mDataList);
        int headViewLayout = getHeadViewLaout();
        if (headViewLayout != 0) {
            View headView = LayoutInflater.from(this).inflate(getHeadViewLaout(), mRvBaseRecycler, false);
            mBaseAdapter.addHeadView(headView);
        }
        mRvBaseRecycler.setAdapter(mBaseAdapter);
        mBaseAdapter.setOnLoadMoreListener(mRvBaseRecycler, new BaseAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                initData();
            }

            @Override
            public boolean isCanLoadMore() {
                return !isPullDown();
            }
        });
        pullDownRefresh();
        firstRefresh();
        mBaseAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(BaseRefreshAct.this, mDataList.get(pos).getDes(), Toast.LENGTH_SHORT).show();
            }
        });
        mBaseAdapter.setOnItemLongClickListener(new BaseAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int pos) {
                Toast.makeText(BaseRefreshAct.this, "long click "+mDataList.get(pos).getDes(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    //获取更多
    protected void pullDownData() {
        page = 1;
        initData();
    }

    //是否正在加载更多
    protected boolean isLoadingMore() {
        return mBaseAdapter.isLoadingMore();
    }

    protected abstract void pullDownComplete();

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("", "get data................");
            if (page == 1) {
                mDataList.clear();
                pullDownComplete();
            } else {
                mBaseAdapter.compeleteLoadmore();
            }
            for (int i = 0; i < 10; i++) {
                mDataList.add(new DataBean("", "item" + (page - 1) + "" + i));
            }
            mBaseAdapter.setBottomEnable(true);
            mBaseAdapter.notifyDataSetChanged();

        }
    };

    /**
     * getData
     */
    protected void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
        }).start();


    }
}