package com.example.cryptochange;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SendcoinFragment2 extends Fragment {
    AnimatorSet animation;
    Animation animation1;
    ImageView imageView;
    TextView textView,textView1;
    Handler handler ,handler1;
    Runnable delayrunnable ,getDelayrunnable1;
    private static SendcoinFragment2 objSendcoinFragment2;

    public static SendcoinFragment2 getInstance() {

        if (objSendcoinFragment2 == null) {
            objSendcoinFragment2 = new SendcoinFragment2();
        }
        return objSendcoinFragment2;
    }
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sendcoin_item2, container, false);
        Button button=rootView.findViewById(R.id.button);
        animation = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
                R.animator.fadeanim);
        animation1 = AnimationUtils.loadAnimation(getContext(),
                R.anim.fade_animation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dilogbox_item);



                imageView = dialog.findViewById(R.id.imageView11);
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_telegram));
                animation.setTarget(imageView);
                animation.start();
                textView = dialog.findViewById(R.id.textView7);
                textView1 = dialog.findViewById(R.id.textView18);
                textView1.setText("Your money have been sent!");
                textView.setVisibility(View.INVISIBLE);
                textView1.setVisibility(View.INVISIBLE);
                handler1 = new Handler();
                getDelayrunnable1 = new Runnable() {
                    @Override
                    public void run() {
                        textView1.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        textView.setAnimation(animation1);
                        textView1.setAnimation(animation1);
                    }
                };
                handler1.postDelayed(getDelayrunnable1, 850);


                dialog.show();
                handler = new Handler();
                delayrunnable = new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                };
                handler.postDelayed(delayrunnable, 5000);
            }
        });
        return rootView;
    }





}