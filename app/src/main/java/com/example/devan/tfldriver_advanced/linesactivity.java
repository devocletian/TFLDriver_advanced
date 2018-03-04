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
import java.util.ArrayList;


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
        Title = Title.replaceAll("_", " ");
        mTextview.setText(Title + " Line");


        //call on different arrays
        Intent b = getIntent();
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
        //String stations = parent.getItemAtPositon(position).toString();
        //toast.makeText(parent.getContext(),stations, Toast.LENGTH_SHORT).show();
        result = spinner.getSelectedItem().toString();
        spinnerview = findViewById(R.id.spinnerview);
        spinnerview.setText(result);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void jsonParse() {
        String url = "https://api.tfl.gov.uk/line/" + linename + "/arrivals";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //the user enters the train number
                EditText train_number;

                train_number = findViewById(R.id.train_number);
                String str = train_number.getText().toString();
                String sta = spinnerview.getText().toString();
                String vehicleId;
                try {
                    TextView mTextViewResult;
                    mTextViewResult = findViewById(R.id.text_view_result);
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject x = response.getJSONObject(i);
                        vehicleId = x.getString("vehicleId");
                        String stationName = x.getString("stationName");
                        String destinationName = x.getString("destinationName");
                        Integer timeToStation = x.getInt("timeToStation");
                        if (vehicleId.equals(str)) {
                            vehicleId = str;
                            mTextViewResult.append(vehicleId + "\n" + "Currently at " + stationName + " going to " + destinationName + " in " + timeToStation / 60 + " minutes" + "\n\n");
                        } else if (destinationName.equals(sta)) {
                                destinationName = sta;
                                mTextViewResult.append(vehicleId + "\n" + " Train to " + destinationName + "\n" +  "from " + stationName + "\n" +  " will arrive in " + timeToStation / 60 + " minutes" + "\n\n");
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


