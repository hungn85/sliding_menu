package com.dichungtaxi.tabs.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	
	List<Fragment> listFragments;
	public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
		super(fm);
		this.listFragments = listFragment;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		// TODO Auto-generated method stub
		return listFragments.get(i);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listFragments.size();
	}

	 

}
