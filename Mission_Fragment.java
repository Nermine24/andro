package com.example.mini_projet_android.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mini_projet_android.MAdapter;
import com.example.mini_projet_android.Mission;
import com.example.mini_projet_android.R;

import java.util.List;

import Retrofit.APIclient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mission_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context context;
    ImageView ajouter;
    TextView add;
    ImageButton addmission;

    public Mission_Fragment() {
        // Required empty public constructor
    }
    public static Mission_Fragment newInstance(String param1, String param2) {
        Mission_Fragment fragment = new Mission_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_mission_fragment,container,false);
        ajouter = (ImageView) rootView.findViewById(R.id.imageView);
        add=(TextView) rootView.findViewById(R.id.add);
        addmission=(ImageButton) rootView.findViewById(R.id.addmission);


        addmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMission();
            }
        });




        Call<List<Mission>>  call = APIclient.getINodeJS_Services().getallmissions();
        call.enqueue(new Callback<List<Mission>>() {
            @Override
            public void onResponse(Call<List<Mission>> call, Response<List<Mission>> response) {


                Response<List<Mission>> ListResponse = response;
                RecyclerView myrv = (RecyclerView) getActivity().findViewById(R.id.missions);
                MAdapter myAdapter = new MAdapter(getContext(),response.body());
                myrv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                myrv.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<List<Mission>> call, Throwable t) {

            }
        });








        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_mission_fragment, container, false);
    }

    private void openMission() {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Intent p = new Intent(getActivity(),AddMission.class);
        startActivity(p);
    }


}