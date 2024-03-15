package com.durvasa.anganwadi_TDC.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.durvasa.anganwadi_TDC.AddStaff;
import com.durvasa.anganwadi_TDC.AddStudent;
import com.durvasa.anganwadi_TDC.AdminDashboard;
import com.durvasa.anganwadi_TDC.ClickInterface;
import com.durvasa.anganwadi_TDC.Pojo.Model;
import com.durvasa.anganwadi_TDC.R;
import com.durvasa.anganwadi_TDC.TotalStaff;
import com.durvasa.anganwadi_TDC.TotalStudent;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {


    ArrayList<Model> arrayList;
    Context context;

    public Adapter(ArrayList<Model> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Model model = arrayList.get(position);
        holder.imageView.setImageResource(model.getImgAdmin());
        holder.total.setText(model.getTotal());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent i = new Intent(context, TotalStudent.class);
                        context.startActivity(i);
                        break;
                    case 1:
                        Intent i1 = new Intent(context, TotalStaff.class);
                        context.startActivity(i1);
                        break;
                    case 2:
                        Intent i2 = new Intent(context, AddStudent.class);
                        context.startActivity(i2);
                        break;
                    case 3:
                        Intent i3 = new Intent(context, AddStaff.class);
                        context.startActivity(i3);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView total;
        RelativeLayout relativeLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            total =itemView.findViewById(R.id.tvTotal);
            relativeLayout = itemView.findViewById(R.id.list_item1);
        }
    }
}
