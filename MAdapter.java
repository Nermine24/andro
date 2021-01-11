package com.example.mini_projet_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

public class MAdapter extends Adapter<MAdapter.MyViewHolder> {
    private Context mcontext;
    private List<Mission> mMissions;
    public MAdapter (Context mcontext,List<Mission>mMissions){
this.mcontext=mcontext;
this.mMissions=mMissions;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){


        View view;
        LayoutInflater mInflater = LayoutInflater.from((Context)mcontext);
        view= mInflater.inflate(R.layout.cardmission,parent,false);
        return new MAdapter.MyViewHolder(view);

    }
    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position)
    {
      Mission mission = mMissions.get(position);
        holder.nomposte.setText(String.valueOf(mission.getNomposte()));
        holder.description.setText(String.valueOf(mission.getDescription()));
        holder.datedeb.setText(String.valueOf((mission.getDatdeb())));
        holder.datefin.setText(String.valueOf((mission.getDatefin())));
        holder.tjm.setText(String.valueOf((mission.getTjm())));




    }
        public int getItemCount() {
            return mMissions.size();
        }


        public static class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView nomposte,description,datedeb,datefin,tjm;
            public MyViewHolder(@NonNull View itemView) {

                super(itemView);

                description=itemView.findViewById(R.id.description);
                nomposte=itemView.findViewById(R.id.nomposte);
                datedeb=itemView.findViewById(R.id.datedeb);
                datefin=itemView.findViewById(R.id.datefin);
                tjm=itemView.findViewById(R.id.tjm);



            }
        }


        }