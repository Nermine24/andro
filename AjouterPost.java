package com.example.mini_projet_android.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class AjouterPost extends AppCompatActivity {

    INodeJS myApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Button ajouter_btn;
    EditText description,userid,titre;

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
        // setContentView(R.layout.activity_2);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_ajouter_post);
        try {
            this.getSupportActionBar().hide();

        }
        catch (NullPointerException e){}
        Retrofit retrofit = RetrofitClient.getInstance();
        myApi= retrofit.create(INodeJS.class);
        //view
        ajouter_btn = (Button) findViewById(R.id.ajouter_btn);
      //  userid = (EditText) findViewById(R.id.userid);
        titre = (EditText) findViewById(R.id.titre);
        description = (EditText) findViewById(R.id.description);

        //EVENT
        ajouter_btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ajouterpost(
                        titre.getText().toString(),
                        description.getText().toString(),
                        userid.getText().toString()


                );
            }
        }));
    }

    private void  ajouterpost(String description, String userid, String titre) {
        compositeDisposable.add(myApi. ajouterpost(description,userid,titre)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("200")){
                            Toast.makeText(AjouterPost.this, "Post ajoutée Avec Succès", Toast.LENGTH_SHORT).show();
                            OpenLogin();

                        }


                    }
                })

        );
    }

    private void OpenLogin() {

    }

    public static class Mission {

        private  String id;
        private String description;
        private String tjm;
        private String datedeb;
        private String datefin;
        private String userid;
        private String nomposte;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTjm() {
            return tjm;
        }

        public void setTjm(String tjm) {
            this.tjm = tjm;
        }

        public String getDatedeb() {
            return datedeb;
        }

        public void setDatedeb(String datedeb) {
            this.datedeb = datedeb;
        }

        public String getDatefin() {
            return datefin;
        }

        public void setDatefin(String datefin) {
            this.datefin = datefin;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getNomposte() {
            return nomposte;
        }

        public void setNomposte(String nomposte) {
            this.nomposte = nomposte;
        }
    }
}