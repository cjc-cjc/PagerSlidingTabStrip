package com.kay.douban;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;

//导入的开源库
import com.astuetz.PagerSlidingTabStrip;


public class MainActivity extends FragmentActivity {

    //起到滑动联动效果，组合语法
    private PagerSlidingTabStrip pagerTab;
    //管理Fragment，是Fragment的容器。FragmentPagerAdapter为ViewPager提供多个Fragment
    private ViewPager pager;
    //页数为4
    public final int NUM_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //得到滑动联动效果的PagerSlidingTabStrip
        pagerTab = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);
        //得到布局中的ViewPager
        pager = (ViewPager) findViewById(R.id.pager);
        //设置屏幕限制的页数为3
        pager.setOffscreenPageLimit(3);
        //为pager设置adapter
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        //为分页管理器加载ViewPager
        pagerTab.setViewPager(pager);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);

    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            //如果是第一页，退出activity
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            //其他情况退回第一页
            // Otherwise, select the first page.
            pager.setCurrentItem(0);
        }
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        //得到每一页的名字
        private final String[] TITLES = getResources().getStringArray(R.array.pager_name);

        //构造函数，调用基类的构造函数
        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //得到页的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        //每个页的内容
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment pagerFragment;
            if (position == 0) {
                //如果是第一页，那么为下拉刷新布局的ListRefreshFragment
                pagerFragment = new ListRefreshFragment();
            } else {
                //其他为PagerFragment
                pagerFragment = new PagerFragment();
            }
            //bundle传递信息
            bundle.putInt("page_num", position);
            //设置Arguments
            pagerFragment.setArguments(bundle);
            //返回fragment
            return pagerFragment;

        }

        //页数为4
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
