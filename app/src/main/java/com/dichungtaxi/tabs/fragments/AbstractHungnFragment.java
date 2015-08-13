package com.dichungtaxi.tabs.fragments;

  
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

abstract public  class AbstractHungnFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(getLayout(), container, false);
		// TODO Auto-generated method stub
		return v;//super.onCreateView(inflater, container, savedInstanceState);
	}
	
	abstract protected int getLayout() ;
}
