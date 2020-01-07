package com.example.cryptochange;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter  extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private ArrayList<HistoryItems> historyItems;
    Context context;
    private int lastPosition = -1;
    long DURATION = 500;
    private boolean on_attach = true;
    HistoryAdapter(ArrayList<HistoryItems> historyItems){
        this.historyItems=historyItems;

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_view, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull final HistoryAdapter.ViewHolder holder, int position){

        HistoryItems objDataItems= historyItems.get(position);
        holder.title.setText(objDataItems.title);

        holder.poster.setImageDrawable(ContextCompat.getDrawable(holder.poster.getContext(),objDataItems.poster));
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_slidefromright_animation);
//        holder.itemView.startAnimation(animation);
//        setAnimation(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return historyItems.size();
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
//        boolean isNotFirstItem = i == -1;
//        i++;
//        itemView.setAlpha(0.f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
//        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
//        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
//        animator.setDuration(500);
//        animatorSet.play(animator);
//        animator.start();

        if (i > lastPosition)
        {
            boolean isNotFirstItem = i == -1;
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.item_slidefromright_animation);
            itemView.startAnimation(animation);

            lastPosition = i;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        ImageView poster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView8);

            poster = itemView.findViewById(R.id.imageView5);
        }
    }
}

