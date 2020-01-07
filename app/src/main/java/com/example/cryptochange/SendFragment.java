package com.example.cryptochange;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SendFragment extends Fragment {


    static Fragment getInstance() {

        return new SendFragment();
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sendcoin_view, container, false);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final SendcoinFragment1 advisorFragment = SendcoinFragment1.getInstance();
        fragmentTransaction.add(R.id.framelayout, advisorFragment);
        fragmentTransaction.commit();
        return rootView;
    }


}
