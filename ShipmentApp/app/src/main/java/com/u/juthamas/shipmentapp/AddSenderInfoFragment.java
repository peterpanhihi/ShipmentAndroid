package com.u.juthamas.shipmentapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;


public class AddSenderInfoFragment extends Fragment {
    private Intent newActivity;
    private View rootView;
    private ArrayList<AtomItem> items;

    public static AddSenderInfoFragment newInstance(ArrayList<AtomItem> list){
        AddSenderInfoFragment send = new AddSenderInfoFragment(list);
        return send;
    }

    public AddSenderInfoFragment(ArrayList<AtomItem> list){
        items = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_sender_info, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        rootView.findViewById(R.id.cancel_btn_sender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddNewItemFragment.newInstance())
                        .commit();
            }
        });

        final EditText name = (EditText)rootView.findViewById(R.id.sender_name);
        final EditText address = (EditText)rootView.findViewById(R.id.sender_Address);
        final EditText city = (EditText)rootView.findViewById(R.id.sender_City);
        final EditText zip = (EditText)rootView.findViewById(R.id.sender_Zip);
        final EditText country = (EditText)rootView.findViewById(R.id.sender_Country);

        rootView.findViewById(R.id.sender_next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] sender = {name.getText().toString(),address.getText().toString()+" "+city.getText().toString()+" "+zip.getText().toString()+" "+country.getText().toString()};
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddReceiverInfoFragment.newInstance(items,sender))
                        .commit();
            }
        });

        return rootView;
    }
}
