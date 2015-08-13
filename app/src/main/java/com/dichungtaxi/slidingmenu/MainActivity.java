package com.dichungtaxi.slidingmenu;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import com.dichungtaxi.slidingmenu.adapters.NavListAdapter;
import com.dichungtaxi.slidingmenu.fragments.AboutFragment;
import com.dichungtaxi.slidingmenu.fragments.HomeFragment;
import com.dichungtaxi.slidingmenu.fragments.SettingFragment;
import com.dichungtaxi.slidingmenu.models.NavItem;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RelativeLayout drawerPane;
    ListView lvNav;
    List<NavItem> listNavItems;
    List<Fragment> listFragments;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //set title
        setTitle(R.string.app_name);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1B7EBA"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);

        //create nav items
        listNavItems = new ArrayList<NavItem>();
        listNavItems.add(new NavItem("Home", "Home page", R.drawable.ic_action_home));
        listNavItems.add(new NavItem("Setting", "Setting page", R.drawable.ic_action_setting));
        listNavItems.add(new NavItem("About", "About page", R.drawable.ic_action_about));

        //set adapter for ListView lvNav
        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext(), R.layout.item_nav_list, listNavItems);
        lvNav.setAdapter(navListAdapter);

        //add com.dichungtaxi.slidingmenu.fragments
        listFragments = new ArrayList<Fragment>();
        listFragments.add(new HomeFragment());
        listFragments.add(new AboutFragment());
        listFragments.add(new SettingFragment());



        //prepare something
        func(0);


        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                func(position);
            }
        });

        //set listner for drawer layout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }

        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void func(int i) {
        //replace main content to list fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content,  listFragments.get(i));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //set title
        //setTitle(listNavItems.get(i).getTitle());
        lvNav.setItemChecked(i, true);
        drawerLayout.closeDrawer(drawerPane);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
