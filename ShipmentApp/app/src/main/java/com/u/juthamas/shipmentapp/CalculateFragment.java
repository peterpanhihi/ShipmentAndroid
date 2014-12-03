package com.u.juthamas.shipmentapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CalculateFragment extends Fragment{

    private ArrayList<AtomItem> newList;
    protected CalculateAdapter adapter;
    protected ListView list;
    private int count = 0;

    public static CalculateFragment newInstance(){
        CalculateFragment calculate = new CalculateFragment();
        return calculate;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_calculate, container,
                false);

        newList = new ArrayList<AtomItem>();

        adapter = new CalculateAdapter(getActivity(), R.layout.atom_calculate_list_item, newList);

        list = (ListView)rootView.findViewById(R.id.cal_listView);
        list.setAdapter(adapter);

        final EditText weight = (EditText)rootView.findViewById(R.id.weight_edit_cal);
        final EditText qtn = (EditText)rootView.findViewById(R.id.qtn_edit_cal);
        final AlertDialog.Builder dDialog = new AlertDialog.Builder(getActivity());

        Button btn = (Button)rootView.findViewById(R.id.add_item_btn_cal);
        btn.setOnClickListener(new View.OnClickListener() {
            double w;
            int q;
            @Override
            public void onClick(View v) {
                count = list.getCount()+1;
                try{
                    w = Double.parseDouble(weight.getText().toString());
                    q = Integer.parseInt(qtn.getText().toString());

                }catch(NumberFormatException e){
                    dDialog.setTitle("Warning");
                    dDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    dDialog.setMessage("Please insert Weight and Quantity");
                    dDialog.setPositiveButton("Close",null);
                    dDialog.show();
                }

                adapter.insert(new AtomItem(String.valueOf(count), w,q), 0);

            }
        });

        Button cal_btn = (Button)rootView.findViewById(R.id.calculate_cal_btn);
        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<AtomItem> items = new ArrayList<AtomItem>();
                for(int i = 0 ;i < list.getCount(); i++){
                    items.add(adapter.getItem(i));
                }

                ShipmentConstant shipmentConstant =  ShipmentConstant.getInstance();
                shipmentConstant.calculateXML(items);

            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(4);
    }

    private class CalculateAdapter extends ArrayAdapter<AtomItem> {

        private List<AtomItem> items;
        private Context context;

        public CalculateAdapter(Context context, int resource, List<AtomItem> items) {
            super(context, resource, items);
            this.context = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if(view == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.atom_calculate_list_item, null);
            }

            AtomItem item = items.get(position);
            if(item != null){
                TextView name = (TextView)view.findViewById(R.id.name_cal);
                TextView weight = (TextView)view.findViewById(R.id.weight_cal);
                TextView quantity = (TextView)view.findViewById(R.id.qtn_cal);
                ImageButton btn = (ImageButton)view.findViewById(R.id.remove_cal);
                btn.setTag(item);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AtomItem itemToRemove = (AtomItem)v.getTag();
                        adapter.remove(itemToRemove);

                        count = list.getCount();
                        for(int i = 0; i < list.getCount(); ++i) {
                            adapter.getItem(i).setName(String.valueOf(count));
                            count--;
                        }
                    }
                });

                if(name != null){
                    name.setText(item.getName());
                }
                if(weight != null){
                    weight.setText(String.valueOf(item.getWeight()));
                }
                if(quantity != null){
                    quantity.setText(String.valueOf(item.getQuantity()));
                }
            }
            return view;
        }
    }
}
