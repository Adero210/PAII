package ceti.edu.paii.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import ceti.edu.paii.MainActivity;
import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.notification.AlarmReceiver;
import ceti.edu.paii.view.fragment.HomeFragment;
import ceti.edu.paii.view.fragment.ProfileFragment;
import ceti.edu.paii.view.fragment.SearchFragment;
//import ceti.edu.paii.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity{

    int notificationid = 1;

    String estado = "";

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "ContainerActivity" ;
    private DatabaseReference mStatusDatabase;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
         firebaseInitialize();



        final HomeFragment homeFragment = new HomeFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        final SearchFragment searchFragment = new SearchFragment();


        notification("Login");

        //final SearchFragment searchFragment = new SearchFragment();
        //set HomeFragment como default en la primera vista despues del login
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottombar);
        bottomNavigationView.setSelectedItemId(R.id.home_menu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home_menu:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.profile_menu:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;
                    case R.id.search_menu:
                        SearchFragment searchFragment = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();
                        break;

                }

                return false;
            }
        });


    }


    private void firebaseInitialize(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Log.w(TAG,"USER LOGIN" + firebaseUser.getEmail());

                }else{
                    Log.w(TAG,"USER no LOGIN");

                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mSignOut:

                notification("Logout");
                mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
                String current_uid = mCurrentUser.getUid();

                mStatusDatabase = FirebaseDatabase.getInstance().getReference().child("not").child(current_uid);
                mStatusDatabase.child("dia").setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),
                                    "Se guardo Exitosamente",
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Se obtuvo un error mientras guardaba",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                firebaseAuth.signOut();
                if(LoginManager.getInstance()!= null){
                    LoginManager.getInstance().logOut();
                }
                SessionManager sessionManager = comun.sessionManager;
                //sessionManager.logout();
                Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ContainerActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.mAbout:
                Toast.makeText(this, "PAII BY Romano and Cornejo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mSettings:
                goChangeUserData();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void goChangeUserData() {
        Intent i = new Intent(ContainerActivity.this, Settings.class);
        startActivity(i);
    }


    private void notification(String est) {


       estado = est;
        String texto = "Recuerda entrar a PAI :)";
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("notificationId" , notificationid);
        intent.putExtra( "todo",texto);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(),
                1,intent,0);


        switch (estado){
           case "Logout":

                Time now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                int hora = now.hour;
                int minute = now.minute+1;
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hora);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                Log.i("TIEMPO", String.valueOf(now));
                Log.i("TIEMPO", String.valueOf(alarmStartTime));
                Log.i("TIEMPO", String.valueOf(hora));
                Log.i("TIEMPO", String.valueOf(minute));

                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                break;

            case "Login":
                Log.i("TIEMPO", "cancelado");

                alarm.cancel(alarmIntent);

                break;

        }

    }

}