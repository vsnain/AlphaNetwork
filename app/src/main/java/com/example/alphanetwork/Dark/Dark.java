package com.example.alphanetwork.Dark;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.alphanetwork.Home.HomeLocalWallFragment;
import com.example.alphanetwork.Home.HomePagerAdapter;
import com.example.alphanetwork.Home.HomeWallFragment;
import com.example.alphanetwork.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


import Utils.BottomNavigationViewHelper;






import Utils.BottomNavigationViewHelper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


public class Dark extends AppCompatActivity{
    private static final String TAG = "Dark";
    private static final int ACTIVITY_NUM = 4;

    private Context mContext = Dark.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark);
        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();
        setupViewPager();
    }


    private void setupViewPager(){
        DarkPagerAdapter adapter = new DarkPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DarkGlobalWallFragment()); //index 0
//        adapter.addFragment(new HomeEventsFragment()); //index 1
        adapter.addFragment(new DarkLocalWallFragment()); //index 1
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_wall);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_wall);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_live);
    }


    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx =  findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}








//public class Dark extends AppCompatActivity{
//    private static final String TAG = "Dark";
//    private static final int ACTIVITY_NUM = 4;
//
//    private Context mContext = Dark.this;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dark);
//        Log.d(TAG, "onCreate: started.");
//
//        setupBottomNavigationView();
//    }
//
//    /**
//     * BottomNavigationView setup
//     */
//    private void setupBottomNavigationView(){
//        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
//        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
//        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
//        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
//        Menu menu = bottomNavigationViewEx.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
//        menuItem.setChecked(true);
//    }
//}