package com.u.juthamas.shipmentapp;

import android.util.Log;

import java.util.ArrayList;

public class ShipmentConstant {

    private String xml;

    public String getAccessToken() {
        return accessToken;
    }

    private String accessToken;

    private boolean isLogin = false;

    private static ShipmentConstant shipment = new ShipmentConstant();

    private ShipmentConstant() {}

    public static ShipmentConstant getInstance(){
        return shipment;
    }

    public void createShipmentXML(ArrayList<AtomItem> items, String[] sender, String[] receiver){
        xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";
        xml += "<shipment>";
        xml += "<courier_name>" + sender[0] + "</courier_name>";
        xml += "<courier_address>" + sender[1] + "</courier_address>";
        xml += "<recieve_name>" + receiver[0] + "</recieve_name>";
        xml += "<recieve_address>" + receiver[1] + "</recieve_address>";
        xml += "<type>EMS</type>";
        for(int i = 0 ; i < items.size(); i++){
            xml += "<item>";
            xml += "<name>" + items.get(i).getName() +"</name>";
            xml += "<weight>" + items.get(i).getWeight() +"</weight>";
            xml += "<quantity>" + items.get(i).getQuantity() +"</quantity>";
            xml += "</item>";
        }
        xml += "</shipment>";
    }

    public void calculateXML(ArrayList<AtomItem> items){
        xml = "<shipment>";
        xml += "<type>weight</type>";
        xml += "<items>";
        for(int i = 0 ; i < items.size(); i++){
            xml += "<item>";
            xml += "<name>" + items.get(i).getName() +"</name>";
            xml += "<weight>" + items.get(i).getWeight() +"</weight>";
            xml += "<quantity>" + items.get(i).getQuantity() +"</quantity>";
            xml += "</item>";
        }
        xml += "</items>";
        xml += "</shipment>";

        Log.d("show calculate xml",xml);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getXml() { return xml; }
}
