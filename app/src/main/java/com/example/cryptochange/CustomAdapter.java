package com.example.cryptochange;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<DataItems> dataItems;
    Context context;
    DrawerClose drawerClose;

    CustomAdapter(ArrayList<DataItems> dataItems, DrawerClose drawerClose) {
        this.dataItems = dataItems;
        this.drawerClose = drawerClose;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        DataItems objDataItems = dataItems.get(position);
        holder.title.setText(objDataItems.title);

        holder.poster.setImageDrawable(ContextCompat.getDrawable(holder.poster.getContext(), objDataItems.poster));
        if (position == 1) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerClose.onDrawerClose();
                    context = v.getContext();
                    Intent intent = new Intent(context, AdddressActivity.class);

                    if (Build.VERSION.SDK_INT > 20) {
                        ActivityOptions options;
                        options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
                        context.startActivity(intent, options.toBundle());
                    } else {
                        context.startActivity(intent);
                    }


                }
            });

        }
        if (position == 4) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerClose.onDrawerClose();
                    context = v.getContext();
                    Intent intent1 = new Intent(context, SecondActivity.class);
                    if (Build.VERSION.SDK_INT > 20) {
                        ActivityOptions options;
                        options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
                        context.startActivity(intent1, options.toBundle());
                    } else {


                        context.startActivity(intent1);
                    }
                }
            });
        }
        if (position == 7) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerClose.onDrawerClose();
                    context = v.getContext();
                    Intent intent1 = new Intent(context, LoginActivity.class);
//                        if(Build.VERSION.SDK_INT>20){
//                            ActivityOptions options;
//                            options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
//                            context.startActivity(intent1,options.toBundle());
//                        }else {
//                            context.startActivity(intent1);

//                        }
//                        ((Activity)context).finish();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public interface DrawerClose {
        void onDrawerClose();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);

            poster = itemView.findViewById(R.id.imageView);
        }
    }
}
