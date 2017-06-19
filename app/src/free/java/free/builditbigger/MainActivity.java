package free.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.JokeClass;
import com.example.nandpa.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.R;
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
public class MainActivity extends AppCompatActivity {

    public String joke = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JokeClass.getJoke();

        MainActivityFragment frg = new MainActivityFragment();
        FragmentManager manager=getSupportFragmentManager();//create an instance of fragment manager

        FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction

        transaction.add(R.id.ads_fragment, frg, "ads_fragment");
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
       /* Intent intent = new Intent(MainActivity.this, com.udacity.gradle.jokedisplay.JokeActivity.class);
        intent.putExtra("Joke key", JokeClass.getJoke());
        startActivity(intent);*/
        new EndpointsAsyncTask().execute(this);
    }


}
