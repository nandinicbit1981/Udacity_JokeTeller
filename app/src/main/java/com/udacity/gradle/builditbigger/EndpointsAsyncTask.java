package com.udacity.gradle.builditbigger;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.JokeClass;
import com.example.nandpa.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.jokedisplay.JokeActivity;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<MainActivity, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private MainActivity mainActivity;

    @Override
    protected String doInBackground(MainActivity... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://udacitybuilditbigger-171219.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        mainActivity = params[0];
        context = mainActivity;
        try {
            return myApiService.sayHi(JokeClass.getJoke()).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }



    @Override
    protected void onPostExecute(String result) {

        mainActivity.joke = result;
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}