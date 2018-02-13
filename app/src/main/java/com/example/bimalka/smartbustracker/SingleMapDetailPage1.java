package com.example.bimalka.smartbustracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SingleMapDetailPage1 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;

    private  double latitudes;
    private double longtitudes;
    private String to;



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION )== PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_map_detail_page1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * @param
     */


    public void setLatitudes(double latitude) {
        this.latitudes = latitude;
    }
    public double getLatitudes() {
        return latitudes;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getTo() {
        return to;
    }

    public void setLongtitudes(double longtitude) {
        this.longtitudes = longtitude;
    }

    public double getLongtitudes() {
        return longtitudes;
    }








    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Add a marker to Show your location and move the camera
                LatLng yourLocation = new LatLng(location.getLatitude(), location.getLongitude());
                //mMap.clear();
                mMap.addMarker(new MarkerOptions().position(yourLocation).title("MyLocation").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_map_man)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(yourLocation));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if(Build.VERSION.SDK_INT<23){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }else{

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }

        // Add a marker in Haldummulla and move the camera

        LatLng haldum = new LatLng(6.771073,80.942974);
        mMap.addMarker(new MarkerOptions().position(haldum).title("ToNuwaraEliya").snippet("Location : Haputale").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_bus_icon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(haldum,10));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent=new Intent(SingleMapDetailPage1.this,BusDetailsPage.class);
                startActivity(intent);
            }
        });



    }
}
