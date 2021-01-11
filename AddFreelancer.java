package com.example.mini_projet_android.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class AddFreelancer extends AppCompatActivity {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText age, description,nom, prenom,github,linkedin,phone,id;
    TextView create;
    Button btn_register, btn_login;
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
        setContentView(R.layout.freelance);

        //INIT API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI=retrofit.create(INodeJS.class);

        //VIEW
        age=(EditText) findViewById(R.id.age);
        phone=(EditText)findViewById(R.id.phone);
        nom=(EditText)findViewById(R.id.nom);
        prenom=(EditText)findViewById(R.id.prenom);
        description=(EditText)findViewById(R.id.description);
       // id=(EditText)findViewById(R.id.id);
        linkedin=(EditText)findViewById(R.id.linkedin);
        github=(EditText)findViewById(R.id.github);
        create=(Button)findViewById(R.id.create);

        /*create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreate();
            }
        });*/
        create.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             ajouterfreelancer(nom.getText().toString(),
                                                     prenom.getText().toString(),
                                                     description.getText().toString(),
                                                     github.getText().toString(),
                                                     linkedin.getText().toString(),
                                                     phone.getText().toString(),
                                                     age.getText().toString());



                                         }
                                     }
        );



    }

    private void openProfile() {
        Intent c = new Intent(this, Cprofile.class);
        startActivity(c);
    }
    private void ajouterfreelancer(String nom, String prenom,String description, String linkedin,String age,String phone,String github) {
        compositeDisposable.add(myAPI.ajouterfreelancer(nom,prenom,description,linkedin,age,phone,github)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("2")) {
                            Toast.makeText(AddFreelancer.this, "Welcome", Toast.LENGTH_LONG).show();
                            openProfile();


                        }

                        else
                            Toast.makeText(AddFreelancer.this, ""+s, Toast.LENGTH_LONG).show();

                    }
                })
        );

    }

}
