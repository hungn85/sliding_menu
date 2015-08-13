package com.dichungtaxi.slidingmenu.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CHIP on 13/08/2015.
 */
abstract public class AbstractFragments extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(getLayout(), container, false);


        return v;
    }

    abstract int getLayout();

}
