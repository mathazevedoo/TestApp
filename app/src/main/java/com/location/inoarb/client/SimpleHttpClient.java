package com.location.inoarb.client;

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class SimpleHttpClient {

    private <R> String executeRequest(R content, String url, String action){

        if(content instanceof String && action.equalsIgnoreCase("put")){
            final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            final RequestBody body = RequestBody.create(JSON, (String) content);
            final OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .addHeader("Content-Type", "application/json")
                    .build();

            try {

                Log.d("Inoarb", "Vai chamar request com \n" + (String) content);
                final Response response = client.newCall(request).execute();
                Log.d("Inoarb", "fechou response \n" + response.body().string());

                return response.body().string();

            }catch (Exception ex){
                ex.printStackTrace();
                return ex.getMessage();
            }
        }

        return "";
    }

    public <R> Thread executeRequestConcurrently(final R content, final String url, final String action){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleHttpClient.this.<R>executeRequest((R)content, url, action);
            }
        });
    }

}
