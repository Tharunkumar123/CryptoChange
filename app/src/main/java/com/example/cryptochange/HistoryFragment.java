package com.example.cryptochange;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class HistoryFragment extends Fragment {
    ArrayList<HistoryItems> historyItems;

    LinearLayoutManager linearLayoutManager;
    LayoutAnimationController animation;
    RecyclerView rv_data;

    static Fragment getInstance() {
        return new HistoryFragment();
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.history_item, container, false);
        rv_data = rootView.findViewById(R.id.history_recycler);
        historyItems = new ArrayList<>();


        historyItems.add(new HistoryItems("0.01735", R.drawable.ic_sync_24px));
        historyItems.add(new HistoryItems("0.02634", R.drawable.ic_check_black_24dp));
        historyItems.add(new HistoryItems("0.01091", R.drawable.ic_error_outline_24px));


        rv_data.setItemAnimator(new SlideInLeftAnimator());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        HistoryAdapter objHistoryAdaptor = new HistoryAdapter(historyItems);
        rv_data.setLayoutManager(linearLayoutManager);

        rv_data.setAdapter(objHistoryAdaptor);

        animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        rv_data.setLayoutAnimation(animation);

    }


}