package com.u.juthamas.shipmentapp;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.u.juthamas.shipmentapp.http.HttpHandler;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;

public class AddReceiverInfoFragment extends Fragment{
    private View rootView;
    private ArrayList<AtomItem>items;
    private String[] sender;

    public static AddReceiverInfoFragment newInstance(ArrayList<AtomItem> list, String[] sender){
        AddReceiverInfoFragment receiver = new AddReceiverInfoFragment(list,sender);
        return receiver;
    }

    public AddReceiverInfoFragment(ArrayList<AtomItem> list, String[] sender) {
        this.items = list;
        this.sender = sender;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_receiver_info, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        rootView.findViewById(R.id.cancel_btn_receiver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity() , "Shipment is canceled!", Toast.LENGTH_SHORT).show();
                
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddNewItemFragment.newInstance())
                        .commit();
            }
        });

        final EditText name = (EditText)rootView.findViewById(R.id.receiver_name);
        final EditText address = (EditText)rootView.findViewById(R.id.receiver_Address);
        final EditText city = (EditText)rootView.findViewById(R.id.receiver_City);
        final EditText zip = (EditText)rootView.findViewById(R.id.receiver_Zip);
        final EditText country = (EditText)rootView.findViewById(R.id.receiver_Country);

        rootView.findViewById(R.id.finish_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] receiver = {name.getText().toString(),address.getText().toString()+" "+city.getText().toString()+" "+zip.getText().toString()+" "+country.getText().toString()};

                final ShipmentConstant shipment = ShipmentConstant.getInstance();
                shipment.createShipmentXML(items, sender, receiver);

                new HttpHandler() {
                    @Override
                    public HttpUriRequest getHttpRequestMethod() {
                        HttpPost httpPost = new HttpPost("http://track-trace.tk:8080/shipments/");
                        httpPost.setHeader("Authorization",shipment.getAccessToken());
                        httpPost.setHeader("Content-Type", "application/xml");

                        try {

                            StringEntity se = new StringEntity(shipment.getXml());
                            httpPost.setEntity(se);

                            Log.d("XMLLLL : ",shipment.getXml());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return httpPost;

//                        return new HttpGet("http://www.google.com");

                    }

                    @Override
                    public void onResponse(String result) {
                        Log.d("Log Response",result);
                        Toast.makeText(getActivity() , "Shipment is created!", Toast.LENGTH_SHORT).show();
                    }
                }.execute();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddNewItemFragment.newInstance())
                        .commit();
            }
        });

        return rootView;
    }
}
