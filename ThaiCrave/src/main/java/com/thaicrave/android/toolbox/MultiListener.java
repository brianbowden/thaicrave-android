package com.thaicrave.android.toolbox;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MultiListener<Model> {

    private TcListener<Model> tcListener;

    protected MultiListener(TcListener<Model> tcListener) {
        this.tcListener = tcListener;
    }

    public static <Model> MultiListener<Model> create(TcListener<Model> tcListener) {
        return new MultiListener<Model>(tcListener);
    }

    public Response.Listener<Model> getSuccessListener() {
        return new Response.Listener<Model>() {
            @Override
            public void onResponse(Model model) {
                tcListener.onResponse(model);
            }
        };
    }

    public Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tcListener.onErrorResponse(volleyError);
            }
        };
    }

    public interface TcListener<Model> {
        public void onResponse(Model response);
        public void onErrorResponse(VolleyError error);
    }
}
