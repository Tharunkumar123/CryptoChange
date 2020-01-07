package com.example.cryptochange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder>{
    private ArrayList<SettingsItem> settingsItems;
    SettingsAdapter(ArrayList<SettingsItem> settingsItems){
        this.settingsItems=settingsItems;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull final SettingsAdapter.ViewHolder holder, int position){

        SettingsItem objSettingsItems= settingsItems.get(position);
        holder.title.setText(objSettingsItems.title);
        holder.content.setText(objSettingsItems.content);
        holder.poster.setImageDrawable(ContextCompat.getDrawable(holder.poster.getContext(),objSettingsItems.poster));

    }

    @Override
    public int getItemCount() {
        return settingsItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        ImageView poster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView5);
            content = itemView.findViewById(R.id.textView6);
            poster = itemView.findViewById(R.id.imageView10);
        }
    }
}
