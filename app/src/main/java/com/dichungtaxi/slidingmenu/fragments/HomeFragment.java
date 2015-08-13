package com.dichungtaxi.slidingmenu.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import com.dichungtaxi.slidingmenu.R;
import com.dichungtaxi.tabs.adapters.MyFragmentPagerAdapter;
import com.dichungtaxi.tabs.fragments.Fragment1;
import com.dichungtaxi.tabs.fragments.Fragment2;
import com.dichungtaxi.tabs.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHIP on 13/08/2015.
 */
public class HomeFragment extends AbstractFragments implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener, View.OnClickListener  {

    ViewPager viewPager;
    TabHost tabHost;
    MyFragmentPagerAdapter myFragmentAdapter;
    View myView;
    @Override
    int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.tabs_activity_main, container, false);


        //fragments
        initFragments();

        //initTabHost
        initTabHost();
        return myView;
    }

    private void initTabHost() {
        tabHost = (TabHost) myView.findViewById(R.id.tabHost);
        tabHost.setup();

        String tabNames [] = {"Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5"};
        for (int i = 0; i < tabNames.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FakeContent implements TabHost.TabContentFactory {

        Context context;
        public FakeContent (Context context) {
            this.context = context;
        }
        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }
    private void initFragments() {

        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add(new Fragment1());
        listFragments.add(new Fragment2());
        listFragments.add(new Fragment3());

        myFragmentAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), listFragments);
        viewPager = (ViewPager) myView.findViewById(R.id.view_pager);
        viewPager.setAdapter(myFragmentAdapter);
        viewPager.setOnPageChangeListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //
    public void onClick(View v) {
        System.out.println(">>>" + v.getId());
        switch (v.getId()) {
            case R.id.test_next_fragment_btn:
                viewPager.setCurrentItem(1, true);
        }
    }


    @Override
    public void onTabChanged(String tabId) {
        viewPager.setCurrentItem(tabHost.getCurrentTab());
        HorizontalScrollView hsv = (HorizontalScrollView)  myView.findViewById(R.id.h_scroll_view);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft() - (hsv.getWidth()-tabView.getWidth())/2;
        hsv.smoothScrollTo(scrollPos, 0);

    }
}
