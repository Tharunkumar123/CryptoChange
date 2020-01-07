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

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>{
private ArrayList<AddressItems> addressItems;
        AddressAdapter(ArrayList<AddressItems> addressItems){
        this.addressItems=addressItems;
        }
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.addressbook_item, parent, false));
        }
@Override
public void onBindViewHolder(@NonNull final AddressAdapter.ViewHolder holder, int position){

        AddressItems objAdderssItems= addressItems.get(position);
        holder.title.setText(objAdderssItems.title);
        holder.content.setText(objAdderssItems.content);
        holder.poster.setImageDrawable(ContextCompat.getDrawable(holder.poster.getContext(),objAdderssItems.poster));

        }

@Override
public int getItemCount() {
        return addressItems.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView content;
    ImageView poster;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.textView5);
        content = itemView.findViewById(R.id.textView2);
        poster = itemView.findViewById(R.id.imageView2);
    }
}
}
