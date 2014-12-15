package com.u.juthamas.shipmentapp.http;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * HttpHandler is basically a process that runs in response to a request made to an ASP.NET Web application.
 * @author Juthamas and Suwijak
 */
public abstract class HttpHandler {
    public abstract HttpUriRequest getHttpRequestMethod();

    public abstract void onResponse(String result);

    public void execute(){
        new AsyncHttpTask(this).execute();
    }
}

