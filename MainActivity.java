package com.example.mini_projet_android.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mini_projet_android.R;
import com.example.mini_projet_android.User;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;

import Retrofit.INodeJS;
import Retrofit.RetrofitClient;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText edt_email, edt_password,id, edt_roles;
    TextView create,freelancer,mission,client;
    Button btn_register,btn_login;
    private LoginButton facebook;

    private CallbackManager  CallbackManager;
    private TextView txtEmail,txtName;
    private CircleImageView circleImageView;
    User User;

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
        setContentView(R.layout.login);

        //INIT API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

        //VIEW
        btn_login = (Button) findViewById(R.id.login_button);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        //id=(EditText)findViewById(R.id.id);
        
        create = (TextView) findViewById(R.id.create);
        freelancer = (TextView) findViewById(R.id.freelancer);
        mission = (TextView) findViewById(R.id.mission);
        client = (TextView) findViewById(R.id.client);
        facebook = findViewById(R.id.facebook);

        CallbackManager =com.facebook.CallbackManager.Factory.create();
        facebook.setReadPermissions(Arrays.asList("email","public_profile"));
       facebook.registerCallback(CallbackManager, new FacebookCallback<LoginResult>() {
           @Override
           public void onSuccess(LoginResult loginResult) {

           }

           @Override
           public void onCancel() {

           }

           @Override
           public void onError(FacebookException error) {

           }
       });


        //EVENT
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
        freelancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFreelance();
            }
        });
        mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMission();
            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClient();
            }
        });

        // EVENT


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edt_email.getText().length() == 0) {
                    edt_email.setError("Email not be empty");
                }
                if (edt_password.getText().length() == 0) {
                    edt_password.setError("Password must not be empty");
                }

                loginUser(edt_email.getText().toString(), edt_password.getText().toString());


            }

        });
    }
    private void openClient() {
        Intent p = new Intent(this, AddClient.class);
        startActivity(p);

    }

    private void openMission() {
        Intent p = new Intent(this,AddMission.class);
        startActivity(p);
    }

    private void openFreelance() {
        Intent c = new Intent(this, AddFreelancer.class);
        startActivity(c);
    }


    private void openRegister() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
    private void openPoster() {


        SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isUserLogin", true);
        editor.commit();

        SharedPreferences userPref = getSharedPreferences ("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor userEditor = userPref.edit ();
        Gson gson = new Gson ();
        String json = gson.toJson (User);
        userEditor.putString ("user", json);
        userEditor.commit ();


        Intent intent = new Intent(this, Fprofile.class);
        startActivity(intent);
    }



    private void loginUser(String email, String password) {
        compositeDisposable.add(myAPI.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("2")) {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                            openPoster();


                        }

                        else
                            Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_LONG).show();

                    }
                })
        );

    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        CallbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode,resultCode,data);
    }
    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken==null)
            {
//                txtName.setText("hhhh");

                      //  txtEmail.setText("");
                       // circleImageView.setImageSource(0);
                        Toast.makeText(MainActivity.this,"User Logged Out", Toast.LENGTH_LONG).show();
            }
            else loadUserProfile(currentAccessToken);

        }
    };
    private void loadUserProfile (AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
           //     try {

//                    String email = object.getString("email");
  //                  String id = object.getString("id");

                    String image_url="https://graph.facebook.com/"+id+"/picture?type=normal";
    //                txtEmail.setText(email);
                  // requestOptions.dontAnimate();
                  //Glide.with(MainActivity.this).load(image_url).into(circleImageView);


             //   } catch (JSONException e) {
               //     e.printStackTrace();
                //}


            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","frist_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
