/**
 * Copyright 2013 Ognyan Bankov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thaicrave.android.toolbox;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;


public class GsonRequest<T> extends JsonRequest<T> {

    private static Gson sGson;

    private static Gson getGson() {
        if (sGson == null) {
            sGson = new Gson();
        }

        return sGson;
    }

    private final Class<T> mClazz;
    private final Object mRequest;
    private final MultiListener<T> mMultiListener;

    public GsonRequest(int method,
                       String url,
                       Object request,
                       Class<T> clazz,
                       MultiListener<T> multiListener) {
        super(method, url, getGson().toJson(request), multiListener.getSuccessListener(), multiListener.getErrorListener());

        mClazz = clazz;
        mMultiListener = multiListener;
        mRequest = request;
    }

    @Override
    protected void deliverResponse(T response) {
        mMultiListener.getSuccessListener().onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(getGson().fromJson(json, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
