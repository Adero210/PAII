package ceti.edu.paii;

import android.app.Application;

import com.facebook.FacebookSdk;

public class PaiiApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        FacebookSdk.sdkInitialize(getBaseContext());


    }
}
