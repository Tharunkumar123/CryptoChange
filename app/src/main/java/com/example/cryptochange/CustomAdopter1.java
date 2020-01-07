package com.example.cryptochange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdopter1 extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    String[] amount1;
    String[] currancy1;
    LayoutInflater inflter;

    public CustomAdopter1(Context applicationContext, int[] flags, String[] countryNames,  String[] amount,String[] currancy) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        this.amount1 = amount;
        this.currancy1 = currancy;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner1, null);

        ImageView icons= view.findViewById(R.id.imageView2);
        TextView names =  view.findViewById(R.id.textView5);
        TextView amount =  view.findViewById(R.id.textView8);
        TextView currancy =  view.findViewById(R.id.textView9);
        ImageView dropdown=view.findViewById(R.id.imageView5);
        icons.setImageResource(flags[i]);
        names.setText(countryNames[i]);
        amount.setText(amount1[i]);
        currancy.setText(currancy1[i]);

        if(i==0){
//            dropdown.setVisibility(View.VISIBLE);
            icons.setVisibility(View.GONE);
            view.setVisibility(View.GONE);

        }
        return view;
    }
}