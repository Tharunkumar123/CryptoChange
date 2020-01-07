package com.example.cryptochange;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.ViewHolder>{
    private ArrayList<BalanceItems> balanceItems;
    long DURATION = 750;
    private boolean on_attach = true;
    BalanceAdapter(ArrayList<BalanceItems> balanceItems){
        this.balanceItems=balanceItems;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.balance_view, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull final BalanceAdapter.ViewHolder holder, int position){

        BalanceItems objAdderssItems= balanceItems.get(position);
        holder.title.setText(objAdderssItems.title);
        holder.content.setText(objAdderssItems.content);
        holder.poster.setImageDrawable(ContextCompat.getDrawable(holder.poster.getContext(),objAdderssItems.poster));
//        setAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return balanceItems.size();
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View itemView, int i) {
        if(!on_attach){
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
        animator.setDuration(750);
        animatorSet.play(animator);
        animator.start();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView content;
        ImageView poster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView5);
            content = itemView.findViewById(R.id.textView9);
            poster = itemView.findViewById(R.id.imageView2);
        }
    }
}