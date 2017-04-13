package android.com.phisicsapp.activities;


import android.com.phisicsapp.R;
import android.com.phisicsapp.database.DbHelper;
import android.com.phisicsapp.model.Settings;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        DbHelper.init(this);
        Settings.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, 1*1000);

    }

}
