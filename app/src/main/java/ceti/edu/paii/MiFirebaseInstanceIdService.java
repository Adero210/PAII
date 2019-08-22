package ceti.edu.paii;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MiFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "FIrebase";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
