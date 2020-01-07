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
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class WalletFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    MaterialButton button;
    AnimatorSet animation2;
    Animation animation1;
    ImageView imageView;
    TextView textView,textView1;
    Handler handler ,handler1;
    Runnable delayrunnable ,getDelayrunnable1;
    ArrayList<HistoryItems> historyItems;
    ArrayList<BalanceItems> balanceItems;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv_data;

    HistoryAdapter objHistoryAdaptor;
    BalanceAdapter objBalanceAdaptor;
    static Fragment getInstance() {
        return new WalletFragment();
    }
    private void startAnimation(){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation);
        rv_data.setLayoutAnimation(layoutAnimationController);
    }
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.wallet_view, container, false);
        tabLayout = rootView.findViewById(R.id.tabs);


        rv_data = rootView.findViewById(R.id.history_recycler);



        balanceItems = new ArrayList<>();


        balanceItems.add(new BalanceItems("Bitcoin","BTC", R.drawable.ic_iconfinder_bitcoin_3838998));
        balanceItems.add(new BalanceItems("Dash","DASH", R.drawable.ic_iconfinder_dash_1175262));
        balanceItems.add(new BalanceItems("Ethereum","ETH", R.drawable.ic_iconfinder_etherium_eth_ethcoin_crypto_2844386));




        linearLayoutManager = new LinearLayoutManager(getContext());
        objBalanceAdaptor = new BalanceAdapter(balanceItems);
        rv_data.setLayoutManager(linearLayoutManager);
        rv_data.setAdapter(objBalanceAdaptor);
        startAnimation();
        historyItems = new ArrayList<>();


        historyItems.add(new HistoryItems("0.01735", R.drawable.ic_sync_24px));
        historyItems.add(new HistoryItems("0.02634", R.drawable.ic_check_black_24dp));
        historyItems.add(new HistoryItems("0.01091", R.drawable.ic_error_outline_24px));


        linearLayoutManager = new LinearLayoutManager(getContext());
        objHistoryAdaptor = new HistoryAdapter(historyItems);
        rv_data.setLayoutManager(linearLayoutManager);
        tabLayout.setSelectedTabIndicatorHeight(0);


        int[] imgResId = {
                R.drawable.balance_icon,
                R.drawable.ic_hourglass
        };

        for (int i = 0; i < imgResId.length; i++) {
            tabLayout.getTabAt(i).setIcon(imgResId[i]);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        rv_data.setAdapter(objBalanceAdaptor);
                        startAnimation();
                        break;
                    case 1:
                        rv_data.setAdapter(objHistoryAdaptor);
                        startAnimation();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




         button=rootView.findViewById(R.id.materialbutton);
        animation2 = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
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
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_iconfinder_wallet222222));
                animation2.setTarget(imageView);
                animation2.start();
                textView = dialog.findViewById(R.id.textView7);
                textView1 = dialog.findViewById(R.id.textView18);
                textView1.setText("Your wallet has been added!");
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


//        View rootView = inflater.inflate(R.layout.wallet_view, container, false);
//        viewPager = rootView.findViewById(R.id.view_pager);
//
//        tabLayout = rootView.findViewById(R.id.tabs);
//
//
//        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
//viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//});
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setSelectedTabIndicatorHeight(0);
//
//
//        int[] imgResId = {
//                R.drawable.balance_icon,
//                R.drawable.ic_hourglass
//
//        };
//
//        for (int i = 0; i < imgResId.length; i++) {
//
//            tabLayout.getTabAt(i).setIcon(imgResId[i]);
//
//
//        }
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
//
//
//                        break;
//                    case 1:
//
//
//                        break;
//
//                }
//
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//
//        return rootView;
//
//    }
//
//
//    public class ViewPagerAdapter extends FragmentPagerAdapter {
//        private Fragment[] childFragments;
//
//
//        public ViewPagerAdapter(@NonNull FragmentManager fm) {
//            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//            childFragments = new Fragment[]{
//
//                    BalanceFragment.getInstance(),
//                    HistoryFragment.getInstance()
//
//            };
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return childFragments[position];
//
//        }
//
//        @Override
//        public int getCount() {
//            return childFragments.length;
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//
//                case 0:
//                    return "Balance";
//                case 1:
//                    return "History";
//
//            }
//
//
//            return null;
//        }
//
//
//    }
//}

