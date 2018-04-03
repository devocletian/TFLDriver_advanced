package com.example.devan.tfldriver_advanced;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openbakerloo(View view) {
        Intent openbakerloointent = new Intent(MainActivity.this, linesactivity.class);
        openbakerloointent.putExtra("Uline", "Bakerloo");
        openbakerloointent.putExtra ("Ustation",R.array.bakerloostations);
        startActivity(openbakerloointent);
        //array
    }
    public void opencentral(View view) {
        Intent opencentralintent = new Intent(MainActivity.this, linesactivity.class);
        opencentralintent.putExtra("Uline", "Central");
        opencentralintent.putExtra ("Ustation",R.array.centralstations);
        startActivity(opencentralintent);
    }
    public void opencircle(View view) {
        Intent opencirclelintent = new Intent(MainActivity.this, linesactivity.class);
        opencirclelintent.putExtra("Uline", "Circle");
        opencirclelintent.putExtra ("Ustation",R.array.circlestations);
        startActivity(opencirclelintent);
    }
    public void opendistrict(View view) {
        Intent opendistrictlintent = new Intent(MainActivity.this, linesactivity.class);
        opendistrictlintent.putExtra("Uline", "district");
        opendistrictlintent.putExtra ("Ustation",R.array.distictstations);
        startActivity(opendistrictlintent);
    }
    public void openhammersmith_and_city(View view) {
        Intent openhammersmith_and_city = new Intent(MainActivity.this, linesactivity.class);
        openhammersmith_and_city.putExtra("Uline","Hammersmith-City");
        openhammersmith_and_city.putExtra ("Ustation",R.array.Hammersmith_and_Citystations);
        startActivity(openhammersmith_and_city);
    }
    public void openjubilee(View view) {
        Intent openjubilee = new Intent(MainActivity.this, linesactivity.class);
        openjubilee.putExtra("Uline", "Jubilee");
        openjubilee.putExtra ("Ustation",R.array.Jubileestations);
        startActivity(openjubilee);
    }
    public void openmetropolitan(View view) {
        Intent openmetropolitan = new Intent(MainActivity.this, linesactivity.class);
        openmetropolitan.putExtra("Uline", "Metropolitan");
        openmetropolitan.putExtra("Ustation", R.array.metropolitanstations);
        startActivity(openmetropolitan);
    }
    public void opennorthern(View view) {
        Intent opennorthern = new Intent(MainActivity.this, linesactivity.class);
        opennorthern.putExtra("Uline", "Northern");
        opennorthern.putExtra ("Ustation",R.array.northernstations);
        startActivity(opennorthern);
    }
    public void openpiccadilly(View view) {
        Intent openpiccadilly = new Intent(MainActivity.this, linesactivity.class);
        openpiccadilly.putExtra("Uline", "Piccadilly");
        openpiccadilly.putExtra ("Ustation",R.array.picadillystations);
        startActivity(openpiccadilly);
    }
    public void openvictoria(View view) {
        Intent openvictoria = new Intent(MainActivity.this, linesactivity.class);
        openvictoria.putExtra("Uline", "Victoria");
        openvictoria.putExtra ("Ustation",R.array.victoriastations);
        startActivity(openvictoria);
    }

}