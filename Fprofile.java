package com.example.mini_projet_android.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mini_projet_android.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class Fprofile extends AppCompatActivity {


    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_freelancer);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(HomeFragment.newInstance("", ""));



        SharedPreferences userPref = getSharedPreferences ("user", Context.MODE_PRIVATE);
        String display = userPref.getString("display","");
       /** Gson gson = new Gson ();
        String json = userPref.getString ("user", "");
        final User session = gson.fromJson (json, User.class);

      System.out.println(json);**/
       try {
           JSONObject p = new JSONObject(display);
           String nermine = p.getString("id");
           System.out.println("nermine");


       }
catch (JSONException e)       {
           e.printStackTrace();
}






        }

    private void openFragment(Fragment f) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, f);
        transaction.addToBackStack(null);
        transaction.commit();

    }

        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                openFragment(HomeFragment.newInstance("", ""));
                                return true;
                            case R.id.navigation_mission:
                                openFragment(Mission_Fragment.newInstance("", ""));
                                return true;
                            case R.id.navigation_about:
                                openFragment(AboutFragment.newInstance("", ""));
                                return true;
                        }
                        return false;
                    }
                };




}