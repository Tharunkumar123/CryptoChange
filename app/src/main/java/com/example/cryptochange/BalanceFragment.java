package com.example.cryptochange;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BalanceFragment extends Fragment {
    static Fragment getInstance() {
        return new BalanceFragment();
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.balance_view, container, false);

//        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.item_slidefromright_animation);
//        rootView.setAnimation(animation);
//        animation.start();
        return rootView;
    }
}
