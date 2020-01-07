package com.example.cryptochange;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdopter2 extends BaseAdapter {
    Context context;
    int images[];
    String[] currancyNames;

    LayoutInflater inflter;

    public CustomAdopter2(Context applicationContext, int[] images, String[] currancyNames) {
        this.context = applicationContext;
        this.images = images;
        this.currancyNames = currancyNames;

        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return images.length;
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
        view = inflter.inflate(R.layout.custom_spinner2, null);

        ImageView icons= view.findViewById(R.id.imageView2);
        TextView names =  view.findViewById(R.id.textView5);

        icons.setImageResource(images[i]);
        names.setText(currancyNames[i]);

        if(i==0){

            view.setVisibility(View.GONE);

        }
        return view;
    }
}