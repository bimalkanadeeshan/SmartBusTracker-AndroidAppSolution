package com.example.bimalka.smartbustracker;

import android.content.Intent;
import android.Manifest;
import android.content.Context;
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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class CommonMapDetailPage extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;




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
        setContentView(R.layout.activity_common_map_detail_page);
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
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap =googleMap;

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

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }

        // Add a marker in Haldummulla and move the camera
        LatLng haldum = new LatLng(6.75922, 80.8808099);
        mMap.addMarker(new MarkerOptions().position(haldum).title("ToColombo").snippet("Location : Haldummulla").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_bus_icon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(haldum,10));

        // Add a marker in Haputale and move the camera
        LatLng hapu = new LatLng(6.7710739, 80.9429741);
        mMap.addMarker(new MarkerOptions().position(hapu).title("ToNuwaraEliya").snippet("Location : Haputale").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_bus_icon)));
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hapu,10));

        // Add a marker in Beragala and move the camera
        LatLng berag = new LatLng(6.76189, 80.9087705);
        mMap.addMarker(new MarkerOptions().position(berag).title("ToPanadura").snippet("Location : Beragala").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_bus_icon)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(berag,10));

        // Add a marker in Balangoda and move the camera
        LatLng balan = new LatLng(6.6630881, 80.6697925);
        mMap.addMarker(new MarkerOptions().position(balan).title("ToElpitiya").snippet("Location : Balangoda").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_bus_icon)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(balan,10));

        // Add a marker in Pelmadulla and move the camera
        LatLng pelm = new LatLng(6.6417578, 80.4927914);
        mMap.addMarker(new MarkerOptions().position(pelm).title("ToEmbilipitiya").snippet("Location : Pelmadulla").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_bus_icon)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pelm,10));

        // Add a marker in Bandarawela and move the camera
        LatLng band = new LatLng(6.8365422, 80.9599636);
        mMap.addMarker(new MarkerOptions().position(band).title("ToBadulla").snippet("Location : Bandarawela").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_bus_icon)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(band,10));




        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent=new Intent(CommonMapDetailPage.this,BusDetailsPage.class);
                startActivity(intent);
            }
        });



    }

}
