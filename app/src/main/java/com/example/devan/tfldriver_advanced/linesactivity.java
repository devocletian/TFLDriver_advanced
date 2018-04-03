package com.example.devan.tfldriver_advanced;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class linesactivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RequestQueue mQueue;
    //Global declaration
    String linename, result;
    Spinner spinner;
    TextView spinnerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);
        TextView mTextview = findViewById(R.id.lines);
        //Uline will change to each specific line
        Intent intent = getIntent();
        linename = intent.getStringExtra("Uline");
        //Produces title
        String Title = linename;
        Title = Title.replaceAll("-", " & ");
        mTextview.setText(Title + " Line");


        //call on different arrays
        Intent b = getIntent();
        // get string list
        int a = b.getIntExtra("Ustation", -1);
        //spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, a, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);


        //json capture
        mQueue = Volley.newRequestQueue(this);
        //the parse is the update button
        Button button_parse = findViewById(R.id.button_parse);
        //when the button is clicked run the following method
        button_parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

    }


    @Override
    public void onItemSelected(final AdapterView<?> parent, View view, int i, long l) {
        result = spinner.getSelectedItem().toString();
        spinnerview = findViewById(R.id.spinnerview);
        spinnerview.setText(result);
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void jsonParse() {
        String url = "https://api.tfl.gov.uk/line/" + linename + "/arrivals";

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //the user enters the train number
                EditText train_numberview;
                TextView mTextViewResult;

                train_numberview = findViewById(R.id.train_number);
                String train_number = train_numberview.getText().toString();
                String sta = spinnerview.getText().toString();
                String vehicleId;


                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String currentDateandTime = sdf.format(new Date());

                try {
                    mTextViewResult = findViewById(R.id.text_view_result);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject x = response.getJSONObject(i);
                        vehicleId = x.getString("vehicleId");
                        String currentLocation = x.getString("currentLocation");
                        String platformName = x.getString("platformName");
                        String stationName = x.getString("stationName");
                        Integer timeToStation = x.getInt("timeToStation");

                        //Search by Train ID
                        if (vehicleId.equals(train_number) && timeToStation<600) {
                            vehicleId = train_number;
                            mTextViewResult.append("Train: " + vehicleId + "\n"
                                    + "Location: " + currentLocation + "\n"
                                    + "Direction: " + platformName + "\n"
                                    +  "Destination: " + stationName + "\n"
                                    + timeToStation / 60 + " minutes away at "
                                    + currentDateandTime +"\n" +"_________________________________________" +"\n" );

                            //Search by Station Name
                        } else if (train_number.equals("") && stationName.equals(sta)) {
                            stationName = sta;
                            mTextViewResult.append("Train: " + vehicleId + "\n"
                                    + "Location: " + currentLocation +  "\n"
                                    + "Direction: " + platformName + "\n"
                                    +  "Destination: " + stationName + "\n"
                                    +   timeToStation / 60 + " minutes away at "
                                    +  currentDateandTime + "\n" +"_________________________________________" +"\n" );
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
}



