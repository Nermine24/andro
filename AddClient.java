package com.example.mini_projet_android.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_projet_android.R;

import Retrofit.RetrofitClient;

import Retrofit.INodeJS;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AddClient extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Button button;
    EditText phone,nb,mf,pays,description,nom;
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
        setContentView(R.layout.activity_add_client);

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI=retrofit.create(INodeJS.class);

        button=(Button)findViewById(R.id.button);
        nom=(EditText)findViewById(R.id.nom);
        pays=(EditText)findViewById(R.id.pays);
        mf=(EditText)findViewById(R.id.mf);
        description=(EditText)findViewById(R.id.description);
        nb=(EditText)findViewById(R.id.nb);
        phone=(EditText)findViewById(R.id.phone);
        //event
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addclient(nom.getText().toString(),
                        pays.getText().toString(),
                        mf.getText().toString(),
                        nb.getText().toString(),
                        description.getText().toString(),
                        phone.getText().toString());
            }}
        );



    }

    private void addclient(String nom, String pays, String mf, String nb, String description, String phone) {


        compositeDisposable.add(myAPI.addclient(nom,pays,mf,description,nb,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("400"))
                            Toast.makeText(AddClient.this, "Client added", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AddClient.this, ""+s, Toast.LENGTH_SHORT).show();

                    }
                })
        );

    }
}