package com.example.cardicrecoder;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.recordviewHolder> {
    ArrayList<Recorditem> recorditems;
    //context for database
    Context context;
    public RecordAdapter(Context context, ArrayList<Recorditem> recorditems) {
        this.recorditems = recorditems;
        this.context=context;
    }

    public static class recordviewHolder extends RecyclerView.ViewHolder{
        TextView heart_beat;
        TextView status;
        TextView comment;
        TextView date;
        TextView time;
        public recordviewHolder(@NonNull View itemView) {
            super(itemView);
            heart_beat=itemView.findViewById(R.id.hbm_s);
            status=itemView.findViewById(R.id.status);
            comment=itemView.findViewById(R.id.comment_s);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);


        }
    }

    @NonNull
    @Override
    public recordviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
        return new recordviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull recordviewHolder holder, int position) {
        holder.heart_beat.setText(recorditems.get(position).getHeart_rate());
        holder.status.setText(recorditems.get(position).getStatus());
        holder.comment.setText(recorditems.get(position).getComment());
        holder.date.setText(recorditems.get(position).getDate());
        holder.time.setText(recorditems.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return recorditems.size();
    }
}
