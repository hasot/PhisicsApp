package android.com.phisicsapp;

import android.app.Application;
import android.com.phisicsapp.activities.MainActivity;
import android.com.phisicsapp.database.DbHelper;
import android.com.phisicsapp.model.Settings;

public class PhysicsAppAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.init(this);
        Settings.init(this);
    }
}
