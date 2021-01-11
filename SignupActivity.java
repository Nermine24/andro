package com.example.mini_projet_android.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class SignupActivity extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText edt_email, edt_password;
    Button btn_register;
    Spinner roles;
    com.example.mini_projet_android.User user;

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
        setContentView(R.layout.signup);
        //INIT API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI=retrofit.create(INodeJS.class);

        btn_register=(Button)findViewById(R.id.register_button);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_password=(EditText)findViewById(R.id.edt_password);
      roles=(Spinner)findViewById(R.id.spinner);

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roles.setAdapter(adapter);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edt_email.getText().length() == 0) {
                    edt_email.setError("Email not be empty");
                }
                if (edt_password.getText().length() == 0) {
                    edt_password.setError("Password must not be empty");
                }

                registerUser(edt_email.getText().toString(),edt_password.getText().toString(),roles.getSelectedItem().toString());
                openFreelance();

            //    public void onSuccessUser(User user) {

               /** if (user.getRoles().equals("Freelancer")) {
                    Intent u = new Intent(SignupActivity.this, AddFreelancer.class);
                    startActivity(u);
                } else if (user.getRoles().equals("Client")) {
                    Intent m = new Intent(SignupActivity.this, AddClient.class);
                    startActivity(m);
                } else {
                    Intent w = new Intent(SignupActivity.this, SignupActivity.class);
                    startActivity(w);
                }**/
           // }
            }


                                        }

        );





    }
    private void openFreelance() {
        Intent c = new Intent(this, AddFreelancer.class);
        startActivity(c);
    }
    private void registerUser(String email, String password, String roles) {
        compositeDisposable.add(myAPI.registerUser(email,password,roles)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("200"))
                        {  Toast.makeText(SignupActivity.this, "Register Success", Toast.LENGTH_LONG).show();
                        openFreelance();     }

                        else
                            Toast.makeText(SignupActivity.this, ""+s, Toast.LENGTH_LONG).show();

                    }
                })
        );

    }
}