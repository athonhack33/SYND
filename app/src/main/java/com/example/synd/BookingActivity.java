package com.example.synd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    Button btnbk, btnhm;
    Spinner spindept, spinslot;
    EditText comm;
    CardView cd;

    TextView tvdt,tvtim,tvdep,tvco;

    DatabaseReference df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        df = FirebaseDatabase.getInstance().getReference("slots");


        btnbk = findViewById(R.id.btnbook);
        spindept = findViewById(R.id.spinnerdept);
        spinslot = findViewById(R.id.spinnerslot);
        comm = findViewById(R.id.comment);

        //cardview
       tvdt = findViewById(R.id.tv_date);
        tvtim = findViewById(R.id.tv_time);
        tvdep = findViewById(R.id.tv_dept);
        tvco = findViewById(R.id.tv_comments);
        cd = findViewById(R.id.cardViewwww);
        btnhm = findViewById(R.id.btnhomeee);

        //spindept
        spindept.setOnItemSelectedListener(this);
        List<String> dept = new ArrayList<String>();
        dept.add("Loan");
        dept.add("Locker");
        dept.add("Dept #3");
        dept.add("Dept #4");
        ArrayAdapter<String> dataAdapterd = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dept);
        dataAdapterd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spindept.setAdapter(dataAdapterd);

        //spinslot
        spinslot.setOnItemSelectedListener(this);
        List<String> slot = new ArrayList<String>();
        slot.add("2:00 - 4:00");
        slot.add("4:00 - 6:00");
        ArrayAdapter<String> dataAdapters = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, slot);
        dataAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinslot.setAdapter(dataAdapters);


        btnbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSlot();
            }
        });

    }

    private void addSlot(){
        String deptt = spindept.getSelectedItem().toString();
        String slott = spinslot.getSelectedItem().toString();
        String com = comm.getText().toString();

        if(!TextUtils.isEmpty(com)){

          /*  String id = df.push().getKey();
            cslot c = new cslot(id, deptt,slott, com);
            df.child(id).setValue(c);
            Toast.makeText(this, "Slot added", Toast.LENGTH_SHORT).show(); */
            cd.setVisibility(View.VISIBLE);
            btnbk.setVisibility(View.INVISIBLE);

            //to be added
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            String date = sdf.format(cal.getTime());
            tvdt.setText(date);

            tvtim.setText(slott);
            tvdep.setText(deptt);
            tvco.setText(com);

            btnhm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BookingActivity.this, MainActivity.class));
                }
            });
        }
        else{
            Toast.makeText(this, "Comment is required!", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}
