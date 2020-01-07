package com.example.cryptochange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExchangeAdapter1 extends BaseAdapter {
    Context context;
    int images1[];
    String[] currancyNames1;

    LayoutInflater inflter;

    public ExchangeAdapter1(Context applicationContext, int[] images1, String[] currancyNames1) {
        this.context = applicationContext;
        this.images1 = images1;
        this.currancyNames1 = currancyNames1;

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return images1.length;
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
        view = inflter.inflate(R.layout.exchange_spinner1, null);

        ImageView icons= view.findViewById(R.id.imageView2);
        TextView names =  view.findViewById(R.id.textView5);

        icons.setImageResource(images1[i]);
        names.setText(currancyNames1[i]);

        if(i==0){

            view.setVisibility(View.GONE);

        }
        return view;
    }
}