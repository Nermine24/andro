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

import com.example.mini_projet_android.Post;
import com.example.mini_projet_android.PostAdapter;
import com.example.mini_projet_android.R;

import java.util.List;

import Retrofit.APIclient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Context context;
    ImageView ajouter;
    TextView add;
    ImageButton addpost;
    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);



        }



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_home_fragment, container, false);
        addpost = (ImageButton) rootView.findViewById(R.id.addpost);
        add=(TextView) rootView.findViewById(R.id.add);
        addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPost();
            }
        });

        Call<List<Post>> call = APIclient.getINodeJS_Services().getallposts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Response<List<Post>> listResponse=response;
                RecyclerView myrv = (RecyclerView) getActivity().findViewById(R.id.posts);
                PostAdapter myAdapter = new PostAdapter(getContext(),response.body());
                myrv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                myrv.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_home_fragment, container, false);
    }

    private void openPost() {
        Intent intent = new Intent(context, AjouterPost.class);


        context.startActivity(intent);
        // Intent p = new Intent(getActivity(),AjouterPost.class);
        // startActivity(p);
    }
}