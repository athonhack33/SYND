package com.example.synd;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference myRef;
    private MarkerOptions options = new MarkerOptions();
    String TAG = "BHAVYA";
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    private ArrayList<String> ar1 = new ArrayList<>();
    private ArrayList<String> ar2 = new ArrayList<>();
    private ArrayList<String> ar3 = new ArrayList<>();

    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        myRef = FirebaseDatabase.getInstance().getReference("Zones");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Zone zn = dataSnapshot1.getValue(Zone.class);
                    double lat = Double.parseDouble(zn.getZoneLat());
                    double longi = Double.parseDouble(zn.getZoneLong());
                    String dep = zn.getZoneTitle();
                    String prob = zn.getZoneData();

                    LatLng sydney = new LatLng(lat,longi);
                    latlngs.add(sydney);
                    ar1.add(dep);
                    ar2.add(prob);
                }

                for (LatLng point : latlngs) {
                    options.position(point);
                    options.title(ar1.get(count));
                    options.snippet(ar2.get(count));
                    mMap.addMarker(options);
                    count = count+1;
                    Log.d("HELLO",point.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng myLoc = new LatLng(19.2389803,72.8557517);
        mMap.addMarker(new MarkerOptions().position(myLoc).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc,10));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng ln = marker.getPosition();
                double latti = ln.latitude;
                double longgi = ln.longitude;
                System.out.println("BUNDLEE"+String.valueOf(latti));

                Bundle bundle = new Bundle();

                /*ar3.add(String.valueOf(latti));
                ar3.add(String.valueOf(longgi));*/

                bundle.putString("LATITUDE",String.valueOf(latti));
                bundle.putString("LONGITUDE",String.valueOf(longgi));

                Intent intent = new Intent(MapsActivity.this,ZoneView.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return false;
            }
        });
    }



}
