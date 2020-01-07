package com.example.cryptochange;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SendcoinFragment1 extends Fragment implements AdapterView.OnItemSelectedListener {

    private String[] countryNames = {"Please select Account", "Dollars", "Bitcoin", "Euro", "Dash", "Monero"};
    private String[] amount = {"", "2.01735", "0.01735", "5.01735", "1.01735", "3.01735"};
    private String[] currancy = {"", "USD", "BTC", "EUR", "DASH", "MNO"};
    private int flags[] = {R.drawable.ic_arrow_drop_down_black_24dp, R.drawable.ic_dollar, R.drawable.ic_iconfinder_bitcoin_3838998, R.drawable.ic_euro, R.drawable.ic_dash, R.drawable.ic_monero};


    private String[] currancyNames = {"Please select Account", "Monero", "Bitcoin", "Dash", "Ethereum", "Dollars"};
    private int images[] = {R.drawable.ic_arrow_drop_down_black_24dp, R.drawable.ic_monero, R.drawable.ic_iconfinder_bitcoin_3838998, R.drawable.ic_dash, R.drawable.ic_iconfinder_etherium_eth_ethcoin_crypto_2844386, R.drawable.ic_dollar};


    public static SendcoinFragment1 getInstance() {

        return new SendcoinFragment1();
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sendcoin_item1, container, false);

        Spinner spin = rootView.findViewById(R.id.spinner);
        Spinner spinner = rootView.findViewById(R.id.spinner2);
        Button button = rootView.findViewById(R.id.materialbutton1);

        spin.setOnItemSelectedListener(this);
        CustomAdopter1 customAdapter = new CustomAdopter1(getContext(), flags, countryNames, amount, currancy);
        spin.setAdapter(customAdapter);


        spinner.setOnItemSelectedListener(this);
        CustomAdopter2 customAdopter2 = new CustomAdopter2(getContext(), images, currancyNames);
        spinner.setAdapter(customAdopter2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final SendcoinFragment2 sendcoinFragment2 = SendcoinFragment2.getInstance();
                fragmentTransaction.replace(R.id.framelayout, sendcoinFragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return rootView;
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}