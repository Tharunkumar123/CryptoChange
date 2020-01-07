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

public class ExchangeFragment1 extends Fragment implements AdapterView.OnItemSelectedListener {


    private String[] currancyNames1 = {"Please select Account", "Monero", "Bitcoin", "Dash", "Ethereum", "Dollars"};
    private int images1[] =  {R.drawable.ic_arrow_drop_down_black_24dp, R.drawable.ic_monero, R.drawable.ic_iconfinder_bitcoin_3838998, R.drawable.ic_dash, R.drawable.ic_iconfinder_etherium_eth_ethcoin_crypto_2844386, R.drawable.ic_dollar};
    private String[] currancyNames2 =  {"Please select Account", "Monero", "Bitcoin", "Dash", "Ethereum", "Dollars"};
    private int images2[] =  {R.drawable.ic_arrow_drop_down_black_24dp, R.drawable.ic_monero, R.drawable.ic_iconfinder_bitcoin_3838998, R.drawable.ic_dash, R.drawable.ic_iconfinder_etherium_eth_ethcoin_crypto_2844386, R.drawable.ic_dollar};



    public static ExchangeFragment1 getInstance() {

        return new ExchangeFragment1();
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.exchange_ietm1, container, false);

        Spinner spin1 = rootView.findViewById(R.id.spinner2);
        Spinner spinner1 = rootView.findViewById(R.id.spinner3);
        Button button = rootView.findViewById(R.id.materialbutton1);

        spin1.setOnItemSelectedListener(this);
        ExchangeAdapter1 exchangeAdapter1 = new ExchangeAdapter1(getContext(), images1, currancyNames1);
        spin1.setAdapter(exchangeAdapter1);


        spinner1.setOnItemSelectedListener(this);

        ExchangeAdapter exchangeAdapter = new ExchangeAdapter(getContext(), images2, currancyNames2);
        spinner1.setAdapter(exchangeAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                final ExchangeFragment2 exchangeFragment2 = ExchangeFragment2.getInstance();
                fragmentTransaction.replace(R.id.framelayout1, exchangeFragment2);
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



