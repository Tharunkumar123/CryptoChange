package com.example.cryptochange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExchangeAdapter extends BaseAdapter {
    Context context;
    int images2[];
    String[] currancyNames2;

    LayoutInflater inflter;

    public ExchangeAdapter(Context applicationContext, int[] images2, String[] currancyNames2) {
        this.context = applicationContext;
        this.images2 = images2;
        this.currancyNames2 = currancyNames2;

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return images2.length;
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
        view = inflter.inflate(R.layout.exchange_spinner2, null);

        ImageView icons= view.findViewById(R.id.imageView2);
        TextView names =  view.findViewById(R.id.textView5);

        icons.setImageResource(images2[i]);
        names.setText(currancyNames2[i]);

        if(i==0){

            view.setVisibility(View.GONE);

        }
        return view;
    }
}