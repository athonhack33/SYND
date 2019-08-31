package com.example.synd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RewardsActivity extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference databaseReference;

    TextView t1,t2;
    String TAG = "BHAVYA";
    int count =0;
    int ccount=0;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        t1= findViewById(R.id.bank_count);
        t2=findViewById(R.id.zone_count);

        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("User_Zones");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    User_Zones zn = dataSnapshot1.getValue(User_Zones.class);
                    String uuid = zn.getUid();
                    if(uuid.equals(uid)){
                        count++;
                    }
                }
                t2.setText(String.valueOf(count));
                t1.setText(String.valueOf(ccount));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
}
