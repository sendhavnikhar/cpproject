package com.example.linkchat_final.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.linkchat_final.R;
import com.example.linkchat_final.chatdetailactivity;
import com.example.linkchat_final.model.user;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.MissingResourceException;

public class useradpter extends  RecyclerView.Adapter<useradpter.viewHolder>{

    ArrayList<user> list;
    Context context;
    private MissingResourceException dataSnapshot;

    public useradpter(ArrayList<user> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.show_user,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        user us = list.get(position);
        Picasso.get().load(us.getProfilePic()).placeholder(R.drawable.avtar).into(holder.image);
        holder.usernaame.setText(us.getUsername());
        holder.lastmassage.setText(us.getLastMassage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , chatdetailactivity.class);
                intent.putExtra("userid", us.getUserid(dataSnapshot.getKey()));
                intent.putExtra("profilePic", us.getProfilePic());
                intent.putExtra("username", us.getUsername());

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView usernaame,lastmassage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.profile_image);
            usernaame = itemView.findViewById(R.id.user);
            lastmassage = itemView.findViewById(R.id.lmsg);
        }
    }
}
