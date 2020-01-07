package com.example.cryptochange;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.Transliterator;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.cryptochange.R.drawable.selected_color;
import static com.example.cryptochange.R.drawable.tab_selector;
import static com.example.cryptochange.R.drawable.unselected_tab;

public class MainActivity extends AppCompatActivity implements CustomAdapter.DrawerClose{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    ArrayList<DataItems> dataItems;

    LinearLayoutManager linearLayoutManager;
    CustomAdapter.DrawerClose drawerClose;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);

        tabLayout = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar action = getSupportActionBar();
        action.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        action.setDisplayHomeAsUpEnabled(true);

        drawerClose=this;
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(0);


        drawerLayout = findViewById(R.id.drawer);

        final NavigationView navigationView = findViewById(R.id.navigation);
//        if (navigationView != null) {
//            setupDrawerContent(navigationView);
//
//        }
        final RecyclerView rv_data= findViewById(R.id.recyclerview);
        dataItems=new ArrayList<>();


        dataItems.add(new DataItems("Wallets",R.drawable.ic_iconfinder_wallet222222));
        dataItems.add(new DataItems("Address book",R.drawable.ic_menu_booknew));
        dataItems.add(new DataItems("Exchange",R.drawable.ic_exchange));
        dataItems.add(new DataItems("Send coins",R.drawable.ic_bitcoin222));
        dataItems.add(new DataItems("Settings",R.drawable.ic_settings));
        dataItems.add(new DataItems("Messages",R.drawable.ic_iconfinder_handyartboard_2_3044139));
        dataItems.add(new DataItems("Rates",R.drawable.ic_reward2323232));
        dataItems.add(new DataItems("Logout",R.drawable.ic_iconfinder_exit_3855614));


        linearLayoutManager =new LinearLayoutManager(this);
        CustomAdapter objCustomAdaptor = new CustomAdapter(dataItems,drawerClose);
        rv_data.setLayoutManager(linearLayoutManager);
        rv_data.setAdapter(objCustomAdaptor);

        int[] imgResId = {
                R.drawable.ic_account_balance_wallet,
                R.drawable.ic_monetization_onew,
                R.drawable.ic_coin_exchange
        };

        for (int i = 0; i < imgResId.length; i++) {

            tabLayout.getTabAt(i).setIcon(imgResId[i]);


        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:



                        toolbar.setTitle("Wallets");
                        break;
                    case 1:


                        toolbar.setTitle("Send coin");


                        break;
                    case 2:

                        toolbar.setTitle("Exchange");



                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                setTabBackGround(tab);
//                tabLayout.setSelected(false);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {


                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
drawerLayout.closeDrawers();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            android.transition.Slide slide = new Slide();
            Slide slide1=new Slide();
            slide1.setSlideEdge(Gravity.END);
            slide.setDuration(500);
            slide.setSlideEdge(Gravity.START);
            slide.setDuration(500);
            slide.setInterpolator(new AccelerateDecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide1);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onDrawerClose() {
        drawerLayout.closeDrawers();
    }



    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private Fragment[] childFragments;


        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            childFragments = new Fragment[]{
                    WalletFragment.getInstance(),
                    SendFragment.getInstance(),
                    ExchangeFragment.getInstance()

            };
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return childFragments[position];

        }

        @Override
        public int getCount() {
            return childFragments.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:
                    return "Wallets";
                case 1:
                    return "Send coins";
                case 2:
                    return "Exchange";
            }


            return null;
        }


    }
}

