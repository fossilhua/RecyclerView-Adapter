# LshRecyclerAdapter
LshRecyclerAdapter 是针对于RecyclerView的adapter。主要功能有添加headview 、加载更多、其中正对瀑布流的footer部分做了特殊处理，使其可以整条显示。

老规矩上图：

![效果图](https://raw.githubusercontent.com/fossilhua/RecyclerView-Adapter/master/raw/gif_1.gif)

[Demo APK下载](https://raw.githubusercontent.com/fossilhua/RecyclerView-Adapter/master/raw/app-debug.apk)

### How to Use

###### 1.Integration jcenter

Please follow the latest publishing aar on jcenter.

follow by the gradle code
```gradle
dependencies{
    compile 'com.github.lsh:recyclerview-adapter:1.0.2'
}
```

###### 2.Usage:

``` 
public class MyAdapter extends BaseAdapter<DataBean> {

    public MyAdapter(Context mContext, List<DataBean> list) {
        super(mContext, list, R.layout.item_recy);
    }

    @Override
    public  void onBindData(BaseViewHolder holder, List<DataBean> data, int position) {
        DataBean dataBean = data.get(position);
        TextView tv = holder.retrieveView(R.id.textView);
        tv.setText(dataBean.getDes());
    }
}



```
Activity中使用
```
 mBaseAdapter.setOnLoadMoreListener(mRvBaseRecycler, new BaseAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //get data
            }
            @Override
            public boolean isCanLoadMore() {
                return !isPullDown();
            }
        });
```

### Version Log

* ***v1.0.1*** 

添加加载更多、head功能

* ***v1.0.2*** 

修复添加headview 第一条数据不显示的bug

### Thanks

[cymcsg/UltimateRecyclerView](https://github.com/cymcsg/UltimateRecyclerView)

[Chanven/CommonPullToRefresh](https://github.com/Chanven/CommonPullToRefresh)



