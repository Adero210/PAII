package ceti.edu.paii.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ceti.edu.paii.MainActivity;
import ceti.edu.paii.R;
import ceti.edu.paii.view.fragment.HomeFragment;
import ceti.edu.paii.view.fragment.ProfileFragment;
import ceti.edu.paii.view.fragment.SearchFragment;
//import ceti.edu.paii.view.fragment.SearchFragment;

public class ContainerActivity extends AppCompatActivity{


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "ContainerActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
         firebaseInitialize();


        final HomeFragment homeFragment = new HomeFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        final SearchFragment searchFragment = new SearchFragment();



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
                firebaseAuth.signOut();
                if(LoginManager.getInstance()!= null){
                    LoginManager.getInstance().logOut();
                }

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
}