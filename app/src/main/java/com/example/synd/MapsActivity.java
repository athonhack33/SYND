package com.example.synd;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    DatabaseReference myRef;
    private MarkerOptions options = new MarkerOptions();
    String TAG = "BHAVYA";
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    private ArrayList<String> ar1 = new ArrayList<>();
    private ArrayList<String> ar2 = new ArrayList<>();
    private ArrayList<String> ar3 = new ArrayList<>();
    LocationManager locationManager;
    int count =0;
    private double latPK;
    private double longPK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        myRef = FirebaseDatabase.getInstance().getReference("Zones");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocation();

    }
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double longi = location.getLongitude();
        Toast.makeText(this, "Latitude: "+ lat+ "\n Longitude: "+longi ,Toast.LENGTH_SHORT).show();

        latPK = lat;
        longPK = longi;
        Log.d("LAT",Double.toString(latPK));
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
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
        LatLng myLoc = new LatLng(latPK,longPK);
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
