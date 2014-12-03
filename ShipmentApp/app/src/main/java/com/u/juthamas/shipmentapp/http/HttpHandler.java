package com.u.juthamas.shipmentapp.http;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by tanawat on 12/3/14 AD.
 */
public abstract class HttpHandler {
    public abstract HttpUriRequest getHttpRequestMethod();

    public abstract void onResponse(String result);

    public void execute(){
        new AsyncHttpTask(this).execute();
    }
}

