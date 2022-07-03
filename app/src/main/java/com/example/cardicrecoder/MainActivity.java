package com.example.cardicrecoder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecordAdapter recordAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Recorditem> recorditems = new ArrayList<>();

    EditText heart_beat;
    EditText systolic_pressure;
    EditText diasystolic_pressure;
    EditText comment;

    Calendar calendar;
    String Date;
    String Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());

        recyclerView=findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        //why linear problem maybe arise
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recordAdapter = new RecordAdapter(this,recorditems);
        recyclerView.setAdapter(recordAdapter);

        calendar=Calendar.getInstance();
        Date=new java.text.SimpleDateFormat("dd-MMM-yyyy").format(calendar.getTime());
        Time=new java.text.SimpleDateFormat("hh:mm a").format(calendar.getTime());
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.datainput_dialolog, null);

        builder.setView(view);
       AlertDialog dialog= builder.create();
       dialog.show();

        heart_beat=view.findViewById(R.id.hbm_i);
        systolic_pressure=view.findViewById(R.id.systolic_pressure_i);
        diasystolic_pressure=view.findViewById(R.id.diastolic_pressure_i);
        comment=view.findViewById(R.id.comment_i);


        Button  cancel=view.findViewById(R.id.cancel);
        Button add=view.findViewById(R.id.add);

        cancel.setOnClickListener(v->dialog.dismiss());
        add.setOnClickListener(v->{
            addrecord();
            dialog.dismiss();
        });


    }

    private void addrecord() {
        String heartbeat=heart_beat.getText().toString();
        String systolicpressure=systolic_pressure.getText().toString();
        String diasystolicpressure=diasystolic_pressure.getText().toString();
        String comnt=comment.getText().toString();
        String date=Date;
        String time=Time;
        String status="Normal pressure";
        int hbm=Integer.parseInt(heartbeat);
        if(hbm>120){
            status="High pressure";
        }
        else if(hbm<80)
        {
            status="low pressure";
        }
        recorditems.add(new Recorditem( heartbeat,systolicpressure,diasystolicpressure,status,date,time,comnt));
        recordAdapter.notifyDataSetChanged();
    }
}