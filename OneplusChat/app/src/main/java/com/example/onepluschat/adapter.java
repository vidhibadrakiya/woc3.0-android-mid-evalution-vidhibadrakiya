package com.example.onepluschat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{

   ArrayList<user> l;
    Context c;

    public adapter(Context c,ArrayList l)
    {
        this.c=c;
        this.l=l;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView i;
        TextView t;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            i=itemView.findViewById(R.id.conim);
            t=itemView.findViewById(R.id.cname);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(c).inflate(R.layout.qwe,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user u = l.get(position);
        holder.t.setText(u.getName());
        Picasso.get().load(u.getPic()).placeholder(R.drawable.person).into(holder.i);


        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, ChatActivity.class);
                intent.putExtra("name", u.getName());
                intent.putExtra("uid", u.getUid());
                c.startActivity(intent);
            }
        });
    }*/

    }

    @Override
    public int getItemCount() {
        return l.size();
    }

}
