package com.example.cryptochange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;

public class AdddressActivity extends AppCompatActivity {
    ArrayList<AddressItems> addressItems;

    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.addressbook_view);

        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);


        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdddressActivity.this,MainActivity.class);
                if(Build.VERSION.SDK_INT>20){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AdddressActivity.this);
                    startActivity(intent,options.toBundle());
                }else {
                    startActivity(intent);
                }

            }
        });
        RecyclerView rv_data= findViewById(R.id.address_recycler);
        addressItems=new ArrayList<>();


        addressItems.add(new AddressItems("Bitcoin","ndo99@gmail.com",R.drawable.ic_iconfinder_bitcoin_3838998));
        addressItems.add(new AddressItems("Monero","ndo99@gmail.com",R.drawable.ic_monero));
        addressItems.add(new AddressItems("Dash","ndo99@gmail.com",R.drawable.ic_dash));
        addressItems.add(new AddressItems("Dollar","ndo99@gmail.com",R.drawable.ic_dollar));
        addressItems.add(new AddressItems("Ethereum","ndo99@gmail.com",R.drawable.ic_iconfinder_etherium_eth_ethcoin_crypto_2844386));
        addressItems.add(new AddressItems("Euro","ndo99@gmail.com",R.drawable.ic_euro));
        addressItems.add(new AddressItems("Bitcoin","ndo99@gmail.com",R.drawable.ic_iconfinder_bitcoin_3838998));
        addressItems.add(new AddressItems("Monero","ndo99@gmail.com",R.drawable.ic_monero));
        addressItems.add(new AddressItems("Dash","ndo99@gmail.com",R.drawable.ic_dash));
        addressItems.add(new AddressItems("Dollar","ndo99@gmail.com",R.drawable.ic_dollar));
        addressItems.add(new AddressItems("Ethereum","ndo99@gmail.com",R.drawable.ic_iconfinder_etherium_eth_ethcoin_crypto_2844386));
        addressItems.add(new AddressItems("Euro","ndo99@gmail.com",R.drawable.ic_euro));
        addressItems.add(new AddressItems("Bitcoin","ndo99@gmail.com",R.drawable.ic_iconfinder_bitcoin_3838998));
        addressItems.add(new AddressItems("Monero","ndo99@gmail.com",R.drawable.ic_monero));
        addressItems.add(new AddressItems("Dash","ndo99@gmail.com",R.drawable.ic_dash));
        addressItems.add(new AddressItems("Dollar","ndo99@gmail.com",R.drawable.ic_dollar));
        addressItems.add(new AddressItems("Ethereum","ndo99@gmail.com",R.drawable.ic_iconfinder_etherium_eth_ethcoin_crypto_2844386));
        addressItems.add(new AddressItems("Euro","ndo99@gmail.com",R.drawable.ic_euro));

        linearLayoutManager =new LinearLayoutManager(this);
        AddressAdapter objAddressAdaptor = new AddressAdapter(addressItems);
        rv_data.setLayoutManager(linearLayoutManager);
        rv_data.setAdapter(objAddressAdaptor);
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
