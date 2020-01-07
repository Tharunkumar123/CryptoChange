package com.example.cryptochange;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NavigationFragment extends Fragment {
    ArrayList<DataItems> dataItems;

    LinearLayoutManager linearLayoutManager;


    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.navigation_view, container, false);

        RecyclerView rv_data= rootView.findViewById(R.id.recyclerview);
        dataItems=new ArrayList<>();


        dataItems.add(new DataItems("Wallets",R.drawable.ic_iconfinder_wallet222222));
        dataItems.add(new DataItems("Address book",R.drawable.ic_menu_booknew));
        dataItems.add(new DataItems("Exchange",R.drawable.ic_exchange));
        dataItems.add(new DataItems("Send coins",R.drawable.ic_monetization_onew));
        dataItems.add(new DataItems("Settings",R.drawable.ic_settings));
        dataItems.add(new DataItems("Messages",R.drawable.ic_new));
        dataItems.add(new DataItems("Rates",R.drawable.ic_emoji_new));
        dataItems.add(new DataItems("Logout",R.drawable.ic_exit_new));


        linearLayoutManager =new LinearLayoutManager(getActivity());
        CustomAdapter objCustomAdaptor = new CustomAdapter(dataItems, null);
        rv_data.setLayoutManager(linearLayoutManager);
        rv_data.setAdapter(objCustomAdaptor);

        return rootView;
    }

}
