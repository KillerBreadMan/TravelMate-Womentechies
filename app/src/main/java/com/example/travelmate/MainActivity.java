package com.example.travelmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import  java.util.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button save;
    ArrayList<ArrayList<Double>> addArray = new ArrayList<ArrayList<Double>>();
    ArrayList<String> strArray = new ArrayList<String>();
    EditText txt;
    ListView show;
    List<String> coordinateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        //mToolbar = (Toolbar)findViewById(R.id.mainpage_toolbar);
        //setSupportActionBar(mToolbar);
        // getSupportActionBar().setTitle("TravelMate");
        txt = (EditText) findViewById(R.id.txtInput);
        show = (ListView) findViewById(R.id.list1);
        save = (Button) findViewById(R.id.button1);
        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String getInput = txt.getText().toString();
                                        if (addArray.contains((getInput))) {
                                            Toast.makeText(getBaseContext(), "Place already added!", Toast.LENGTH_LONG).show();
                                        } else if (getInput == null || getInput.trim().equals("")) {
                                            Toast.makeText(getBaseContext(), "Please enter co-ordinates!", Toast.LENGTH_LONG).show();
                                        } else {
                                            String[] splitInp = getInput.split(",");
                                            //coordinateList = new ArrayList<>();
                                            coordinateList.add(getInput);
//                    double x = Double.parseDouble(splitInp[0]);
//                    double y = Double.parseDouble(splitInp[1]);
//                    double[] xy = {x,y};
//                    ArrayList<Double> elementWise = new ArrayList<Double>();
//                    elementWise.add(x);
//                    elementWise.add(y);
//                    addArray.add(elementWise);
                                            strArray.add(getInput);
                                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, strArray);
                                            show.setAdapter(adapter);
                                            ((EditText) findViewById(R.id.txtInput)).setText("");
                                        }
                                    }
                                }
        );
    }

    public void showBest(View view) {
        Intent startAct = new Intent(this, districts_activity.class);
//      String coordinates = coordinateList.get(0);
        Double[][] array = new Double[addArray.size()][];
        for (int i = 0; i < addArray.size(); i++) {
            ArrayList<Double> row = addArray.get(i);
            array[i] = row.toArray(new Double[row.size()]);
        }
        Bundle bundle = new Bundle();
        double[][] bestPath;
        bestPath = optimised_tsm(coordinateList.toArray(new String[coordinateList.size()]));
        bundle.putSerializable("list", bestPath);
        startAct.putExtras(bundle);
        startActivity(startAct);
    }

    public Double distance(double x[], double y[]) {
        return Math.sqrt((Math.pow((x[0] - y[0]), 2) + Math.pow((x[1] - y[1]), 2)));
    }

    public double[][] optimised_tsm(String[] S) {
        System.out.println("input is:" + Arrays.toString(S));
        int i;
        int[] vis = new int[S.length];
        Arrays.fill(vis, 1);
        int sum = 1; //as long as something is in there
        double[][] all = new double[100][100];
        for (i = 0; i < S.length; i++) {
            double[] element = new double[2];
            element[0] = Double.parseDouble(S[i].split(",")[0]);
            element[1] = Double.parseDouble(S[i].split(",")[1]);
            all[i] = element;
        }
        //populating all matrix
        vis[0]=0;
        int j;
        double[][] path = new double[100][100];
        int pathcounter = 1;
        path[0]=all[0];
        double[] maxdub = new double[]{9999999,9999999};
        for(j=0;j<S.length;j++){
            int index = 0;
            double leastdist = 1000;
            for (i=1;i<S.length;i++){
                if (leastdist>distance(path[pathcounter],all[i])){
                index = i;
                leastdist = distance(path[pathcounter],all[i]);}
            }
            path[pathcounter] = all[index];
            all[index]=maxdub;
            vis[index]=0;
            pathcounter++;
        }
        for(i=0;i<S.length;i++){
            System.out.println("Path element " + i+ "is"+ Arrays.toString(path[i]));
        }
        System.out.println("S length is " + S.length);
        return path;
    }
}