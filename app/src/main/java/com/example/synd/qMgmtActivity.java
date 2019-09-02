package com.example.synd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class qMgmtActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference myRef;
    TextView t1,t2,t3,t4;
    String TAG = "BHAVYA";

    String pass,cash,dd,loan;

    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_mgmt);

        t1 = findViewById(R.id.passbook_text);
        t2 = findViewById(R.id.cash_text);
        t3 = findViewById(R.id.dd_text);
        t4 = findViewById(R.id.loan_text);

        bt = findViewById(R.id.button);
        bt.setOnClickListener(this);

        myRef = FirebaseDatabase.getInstance().getReference("Crowd_Count");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cash=dataSnapshot.child("cashcounter").getValue(String.class);
                pass=dataSnapshot.child("passbook").getValue(String.class);
                dd=dataSnapshot.child("cheque").getValue(String.class);
                loan=dataSnapshot.child("loan_dept").getValue(String.class);

                t1.setText(pass);
                t2.setText(cash);
                t3.setText(dd);
                t4.setText(loan);

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });






    }

    @Override
    public void onClick(View view) {
        if(view==bt){
            Crowd_Count cn = new Crowd_Count("10","5","7","4");
            myRef.setValue(cn);
        }

    }
}
