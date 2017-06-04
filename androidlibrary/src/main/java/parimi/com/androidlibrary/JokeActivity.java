package parimi.com.androidlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);
        TextView txtView = (TextView) findViewById(R.id.joke);
        txtView.setText(getIntent().getExtras().getString("jokeFromApp"));
        //jokeText.setText(joke);
    }
}
