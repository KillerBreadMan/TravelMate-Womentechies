package com.example.travelmate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;

public class districts_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.districts_activity);
        //Double[][] array = (Double[][]) bundle.getSerializable("list");
        Bundle bundle = getIntent().getExtras();
        Double[][] array = (Double[][]) bundle.getSerializable("list");
        int i;
        for (i=0;i<array.length;i++){

    }

    }
}
