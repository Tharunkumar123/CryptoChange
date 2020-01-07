package com.example.cryptochange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ArrayList<SettingsItem> settingsItems;

    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.settings_view);
        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                if(Build.VERSION.SDK_INT>20){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    startActivity(intent);
                }

            }
        });
        RecyclerView rv_data= findViewById(R.id.settings_recycler);
        settingsItems=new ArrayList<>();


        settingsItems.add(new SettingsItem("PIN security","Setup a PIN security for every critical mobile wallet operation",R.drawable.ic_dialpad_black_24dp));
        settingsItems.add(new SettingsItem("2FA security","Enable 2FA(two-factor-authorization) works for both mobile and web wallet.",R.drawable.ic_call_black_24dp));
        settingsItems.add(new SettingsItem("Change password","Set a new password to your BIT AC account",R.drawable.ic_lock_black_24dp));




        linearLayoutManager =new LinearLayoutManager(this);
        SettingsAdapter objSettingsAdaptor = new SettingsAdapter(settingsItems);
        rv_data.setLayoutManager(linearLayoutManager);
        rv_data.setAdapter(objSettingsAdaptor);
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



}
