package com.example.mini_projet_android.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_projet_android.R;

import Retrofit.INodeJS;
import Retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AddMission extends AppCompatActivity {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Button add;
    EditText userid,nomposte,datedeb,datefin,description,tjm;
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mission);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI=retrofit.create(INodeJS.class);

        add=(Button)findViewById(R.id.add);
       // userid=(EditText)findViewById(R.id.userid);
        nomposte=(EditText)findViewById(R.id.nomposte);
        datedeb=(EditText) findViewById(R.id.datedeb);
        datefin=(EditText)findViewById(R.id.datefin);
        description=(EditText)findViewById(R.id.description);
        tjm=(EditText)findViewById(R.id.tjm);
//event
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
                String display = userPref.getString("user","");
                Log.d("user",display);
                //try {
                  //  JSONObject  p= new JSONObject(display);
                    //id.setText( p.getString("NomPrenomClient"));
               // } catch (JSONException e) {
                  //  e.printStackTrace();
                //}
                ajoutermission(
                        nomposte.getText().toString(),
                        datedeb.getText().toString(),
                        datefin.getText().toString(),
                        description.getText().toString(),
                        tjm.getText().toString());
            }}
            );
    }

    private void ajoutermission( String nomposte, String tjm, String description, String datedeb, String datefin) {

        compositeDisposable.add(myAPI.ajoutermission(nomposte,tjm,description,datedeb,datefin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("400"))
                            Toast.makeText(AddMission.this, "Mission added", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddMission.this, ""+s, Toast.LENGTH_SHORT).show();

                    }
                })
        );
    }


}