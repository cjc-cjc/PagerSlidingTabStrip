package com.kay.douban;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//这个是下拉布局的listview实现，也就是点击豆瓣时的fragment
/*
The SwipeRefreshLayout should be used whenever the user can refresh the contents of a view via a vertical swipe gesture. The activity that instantiates this view should add an OnRefreshListener to be notified whenever the swipe to refresh gesture is completed. The SwipeRefreshLayout will notify the listener each and every time the gesture is completed again; the listener is responsible for correctly determining when to actually initiate a refresh of its content. If the listener determines there should not be a refresh, it must call setRefreshing(false) to cancel any visual indication of a refresh. If an activity wishes to show just the progress animation, it should call setRefreshing(true). To disable the gesture and progress animation, call setEnabled(false) on the view.

This layout should be made the parent of the view that will be refreshed as a result of the gesture and can only support one direct child. This view will also be made the target of the gesture and will be forced to match both the width and the height supplied in this layout. The SwipeRefreshLayout does not provide accessibility events; instead, a menu item must be provided to allow refresh of the content wherever this gesture is used.
*/



/**
 * A simple {@link Fragment} subclass.
 */
public class ListRefreshFragment extends android.support.v4.app.Fragment
        implements SwipeRefreshLayout.OnRefreshListener {


    //下拉布局
    private SwipeRefreshLayout mRefreshLayout;
    //布局里面的列表项
    private ListView lv;

    private String names[] = new String[] {
            "乔峰","郭靖","杨过","张无忌",
            "段誉","王姑娘","黄蓉","赵敏"
    };

    private String[] desc = new String[] {
        "降龙十八掌","降龙十八掌","黯然消魂掌","乾坤大挪移",
            "六脉神剑","看招拆招","打狗棒法","有钱"
    };

    private int[] imageIds = new int[] {
            R.drawable.q,R.drawable.g,R.drawable.y,R.drawable.z,
            R.drawable.d,R.drawable.w,R.drawable.h,R.drawable.m
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_refresh, container, false);
        //找到android.support.v4.widget.SwipeRefreshLayout下拉布局
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        //设置监听器，fragment实现了OnRefreshListener接口
        mRefreshLayout.setOnRefreshListener(this);
        //设置刷新过程中，圈圈的颜色变化
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        List<Map<String,Object>> listItems =
                new ArrayList<>();
        for(int i=0;i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("header", imageIds[i]);
            listItem.put("personName", names[i]);
            listItem.put("desc", desc[i]);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
                listItems,R.layout.simple_item,
                new String[] {  "personName","header","desc" },
                new int[] {R.id.name,R.id.header,R.id.desc} );

        //找到布局的ListView
        lv = (ListView) view.findViewById(R.id.lv);

        lv.setAdapter(simpleAdapter);
        //返回布局视图
        return view;
    }



    //重写监听器的onRresh方法,下拉刷新时，启动新线程，延迟三秒启动
    @Override
    public void onRefresh() {
        //刷新
        mRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                //刷新停止
                mRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }


}
