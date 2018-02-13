package com.example.bimalka.smartbustracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
import java.util.ArrayList;
import java.util.List;


public class HomePage extends AppCompatActivity {

    private TextView txtRow1;
    private TextView txtRow11;
    private TextView txtRow111;
    private TextView txtRow1111;
    private TextView txtRow2;
    private TextView txtRow22;
    private TextView txtRow222;
    private TextView txtRow2222;
    private TextView txtRow3;
    private TextView txtRow33;
    private TextView txtRow333;
    private TextView txtRow3333;
    private TextView txtRow4;
    private TextView txtRow44;
    private TextView txtRow444;
    private TextView txtRow4444;
    private TextView txtRow5;
    private TextView txtRow55;
    private TextView txtRow555;
    private TextView txtRow5555;
    private TextView txtRow6;
    private TextView txtRow66;
    private TextView txtRow666;
    private TextView txtRow6666;

    DatabaseReference databaseReference1;
    DatabaseReference databaseReference11;
    DatabaseReference databaseReference111;
    DatabaseReference databaseReference1111;
    DatabaseReference databaseReference2;
    DatabaseReference databaseReference22;
    DatabaseReference databaseReference222;
    DatabaseReference databaseReference2222;
    DatabaseReference databaseReference3;
    DatabaseReference databaseReference33;
    DatabaseReference databaseReference333;
    DatabaseReference databaseReference3333;
    DatabaseReference databaseReference4;
    DatabaseReference databaseReference44;
    DatabaseReference databaseReference444;
    DatabaseReference databaseReference4444;
    DatabaseReference databaseReference5;
    DatabaseReference databaseReference55;
    DatabaseReference databaseReference555;
    DatabaseReference databaseReference5555;
    DatabaseReference databaseReference6;
    DatabaseReference databaseReference66;
    DatabaseReference databaseReference666;
    DatabaseReference databaseReference6666;
    DatabaseReference databaseReferenceA1;
    DatabaseReference databaseReferenceA2;
    DatabaseReference databaseReferenceA3;
    DatabaseReference databaseReferenceA4;
    DatabaseReference databaseReferenceA5;
    DatabaseReference databaseReferenceA6;
    DatabaseReference databaseReferenceA7;
    DatabaseReference databaseReferenceA8;
    DatabaseReference databaseReferenceA9;
    DatabaseReference databaseReferenceA10;
    DatabaseReference databaseReferenceA11;
    DatabaseReference databaseReferenceA12;
    DatabaseReference databaseReferenceA13;
    DatabaseReference databaseReferenceA14;
    DatabaseReference databaseReferenceA15;
    DatabaseReference databaseReferenceA16;
    DatabaseReference databaseReferenceA17;
    DatabaseReference databaseReferenceA18;


    private EditText startingLocation;
    private EditText destination;
    private Button save;
    private Button search;

    public double lat;
    public double longt;
    public String toPoint;

    DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        startingLocation = (EditText) findViewById(R.id.txtFrom);
        destination = (EditText) findViewById(R.id.txtTo);
        save = (Button) findViewById(R.id.btnSave);
        search = (Button) findViewById(R.id.btnSearch);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("SavedSearch");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTable();
            }
        });
    }


    public void addData() {
        String StartingLocation = startingLocation.getText().toString().trim();
        String Destination = destination.getText().toString().trim();

        SaveFavRoutes saveFavRoutes = new SaveFavRoutes(StartingLocation, Destination);
        databaseReference.push().setValue(saveFavRoutes);
        Toast.makeText(getApplication(), "Saved Successfully", Toast.LENGTH_LONG).show();

    }
    public String getDistance(final double lat1, final double lon1, final double lat2, final double lon2) {

        final String[] parsedDistance = new String[1];
        final String[] parseDuration = new String[1];
        final String[] parseLocation = new String[1];
        Thread thread = new Thread(new Runnable() {
            String response;

            @Override
            public void run() {
                //final String parsedDistance;
                try {

                    URL url = new URL("http://maps.googleapis.com/maps/api/directions/json?origin=" + lat1 + "," + lon1 + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric&mode=driving");
                    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("routes");
                    JSONObject routes = array.getJSONObject(0);
                    JSONArray legs = routes.getJSONArray("legs");
                    JSONObject steps = legs.getJSONObject(0);
                    JSONObject distance = steps.getJSONObject("distance");
                    JSONObject duration = steps.getJSONObject("duration");

                    parsedDistance[0] = distance.getString("text");
                    String s = parsedDistance[0];

                    parseDuration[0] = duration.getString("text");
                    String y = parseDuration[0];

                    parseLocation[0] = steps.getString("end_address");
                    String z = parseLocation[0];

                    Toast.makeText(getApplication(), "Distance :"+s+" Time Duration :"+y+" Location :"+z, Toast.LENGTH_LONG).show();



                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return parsedDistance[0];
    }



    public void addTable() {
        txtRow1 = (TextView) findViewById(R.id.txtRow1);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        databaseReference1 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L46mCuhaRADsEbvrrz_").child("busNumber");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow2 = (TextView) findViewById(R.id.txtRow2);

        databaseReference2 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48yw4vEe0QQ5tosbTN").child("busNumber");

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow3 = (TextView) findViewById(R.id.txtRow3);

        databaseReference3 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48zLcSBO6HXAKik-OC").child("busNumber");

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow4 = (TextView) findViewById(R.id.txtRow4);

        databaseReference4 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L490IW6pkScf_wolNG5").child("busNumber");

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow5 = (TextView) findViewById(R.id.txtRow5);

        databaseReference5 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L491HowKYxy74pNM3M6").child("busNumber");

        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow5.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow6 = (TextView) findViewById(R.id.txtRow6);

        databaseReference6 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L49IBX_tlkt-mUBBLV8").child("busNumber");

        databaseReference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow6.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow11 = (TextView) findViewById(R.id.txtRow11);

        databaseReference11 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L46mCuhaRADsEbvrrz_").child("from");

        databaseReference11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow11.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow22 = (TextView) findViewById(R.id.txtRow22);

        databaseReference22 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48yw4vEe0QQ5tosbTN").child("from");

        databaseReference22.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow22.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow33 = (TextView) findViewById(R.id.txtRow33);

        databaseReference33 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48zLcSBO6HXAKik-OC").child("from");

        databaseReference33.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow33.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow44 = (TextView) findViewById(R.id.txtRow44);

        databaseReference44 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L490IW6pkScf_wolNG5").child("from");

        databaseReference44.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow44.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow55 = (TextView) findViewById(R.id.txtRow55);

        databaseReference55 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L491HowKYxy74pNM3M6").child("from");

        databaseReference55.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow55.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow66 = (TextView) findViewById(R.id.txtRow66);

        databaseReference66 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L49IBX_tlkt-mUBBLV8").child("from");

        databaseReference66.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow66.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow111 = (TextView) findViewById(R.id.txtRow111);

        databaseReference111 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L46mCuhaRADsEbvrrz_").child("to");

        databaseReference111.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow111.setText(value);
                txtRow1111 = (TextView) findViewById(R.id.txtRow1111);
                txtRow1111.setText("31 mins");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow222 = (TextView) findViewById(R.id.txtRow222);

        databaseReference222 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48yw4vEe0QQ5tosbTN").child("to");

        databaseReference222.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow222.setText(value);
                txtRow2222 = (TextView) findViewById(R.id.txtRow2222);
                txtRow2222.setText("56 mins");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow333 = (TextView) findViewById(R.id.txtRow333);

        databaseReference333 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48zLcSBO6HXAKik-OC").child("to");

        databaseReference333.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow333.setText(value);
                txtRow3333 = (TextView) findViewById(R.id.txtRow3333);
                txtRow3333.setText("37 mins");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow444 = (TextView) findViewById(R.id.txtRow444);

        databaseReference444 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L490IW6pkScf_wolNG5").child("to");

        databaseReference444.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow444.setText(value);
                txtRow4444 = (TextView) findViewById(R.id.txtRow4444);
                txtRow4444.setText("41 min");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow555 = (TextView) findViewById(R.id.txtRow555);

        databaseReference555 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L491HowKYxy74pNM3M6").child("to");

        databaseReference555.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow555.setText(value);
                txtRow5555 = (TextView) findViewById(R.id.txtRow5555);
                txtRow5555.setText("75 min");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        txtRow666 = (TextView) findViewById(R.id.txtRow666);

        databaseReference666 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L49IBX_tlkt-mUBBLV8").child("to");

        databaseReference666.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtRow666.setText(value);
                txtRow6666 = (TextView) findViewById(R.id.txtRow6666);
                txtRow6666.setText("78 min");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });






    }



    public void viewMap1(View view) {


        databaseReferenceA1 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L46mCuhaRADsEbvrrz_").child("latitude");

        databaseReferenceA1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double latitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLatitudes(latitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA2 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L46mCuhaRADsEbvrrz_").child("longtitude");

        databaseReferenceA2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double longtitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLongtitudes(longtitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA3 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L46mCuhaRADsEbvrrz_").child("to");

        databaseReferenceA3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strTo = dataSnapshot.getValue().toString();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setTo(strTo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        Intent intent=new Intent(getApplicationContext(),SingleMapDetailPage.class);
        startActivity(intent);
    }

    public void viewMap2(View view) {
        databaseReferenceA4 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48yw4vEe0QQ5tosbTN").child("latitude");

        databaseReferenceA4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double latitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLatitudes(latitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA5 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48yw4vEe0QQ5tosbTN").child("longtitude");

        databaseReferenceA5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double longtitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLongtitudes(longtitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA6 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48yw4vEe0QQ5tosbTN").child("to");

        databaseReferenceA6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strTo = dataSnapshot.getValue().toString();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setTo(strTo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        Intent intent=new Intent(getApplicationContext(),SingleMapDetailPage1.class);
        startActivity(intent);
    }

    public void viewMap3(View view) {
        databaseReferenceA7 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48zLcSBO6HXAKik-OC").child("latitude");

        databaseReferenceA7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double latitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLatitudes(latitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA8 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48zLcSBO6HXAKik-OC").child("longtitude");

        databaseReferenceA8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double longtitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLongtitudes(longtitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA9 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L48zLcSBO6HXAKik-OC").child("to");

        databaseReferenceA9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strTo = dataSnapshot.getValue().toString();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setTo(strTo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        Intent intent=new Intent(getApplicationContext(),SingleMapDetailsPage2.class);
        startActivity(intent);
    }

    public void viewMap4(View view) {
        databaseReferenceA10 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L490IW6pkScf_wolNG5").child("latitude");

        databaseReferenceA10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double latitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLatitudes(latitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA11 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L490IW6pkScf_wolNG5").child("longtitude");

        databaseReferenceA11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double longtitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLongtitudes(longtitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA12 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L490IW6pkScf_wolNG5").child("to");

        databaseReferenceA12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strTo = dataSnapshot.getValue().toString();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setTo(strTo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        Intent intent=new Intent(getApplicationContext(),SingleMapDetailPage3.class);
        startActivity(intent);
    }

    public void viewMap5(View view) {
        databaseReferenceA13 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L491HowKYxy74pNM3M6").child("latitude");

        databaseReferenceA13.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double latitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLatitudes(latitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA14 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L491HowKYxy74pNM3M6").child("longtitude");

        databaseReferenceA14.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double longtitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLongtitudes(longtitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA15 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L491HowKYxy74pNM3M6").child("to");

        databaseReferenceA15.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strTo = dataSnapshot.getValue().toString();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setTo(strTo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        Intent intent=new Intent(getApplicationContext(),SingleMapDetailPage4.class);
        startActivity(intent);
    }

    public void viewMap6(View view) {
        databaseReferenceA16 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L49IBX_tlkt-mUBBLV8").child("latitude");

        databaseReferenceA16.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double latitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLatitudes(latitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA17 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L49IBX_tlkt-mUBBLV8").child("longtitude");

        databaseReferenceA17.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double longtitude = (Double) dataSnapshot.getValue();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setLongtitudes(longtitude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        databaseReferenceA18 = FirebaseDatabase.getInstance("https://busticketmachine-2095c.firebaseio.com/").getReference().child("BusTrackingDetails").child("-L49IBX_tlkt-mUBBLV8").child("to");

        databaseReferenceA18.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strTo = dataSnapshot.getValue().toString();
                SingleMapDetailPage smdp = new SingleMapDetailPage();
                smdp.setTo(strTo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });


        Intent intent=new Intent(getApplicationContext(),SingleMapDetailPage5.class);
        startActivity(intent);
    }

    public void viewCommonMap(View view) {
        Intent intent = new Intent(getApplicationContext(), CommonMapDetailPage.class);
        startActivity(intent);

    }

    public void viewFavouriteTrackings(View view) {
        Intent intent = new Intent(getApplicationContext(), FavouriteTrackingsPage.class);
        startActivity(intent);

    }
}