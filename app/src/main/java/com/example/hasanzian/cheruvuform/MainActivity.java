package com.example.hasanzian.cheruvuform;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    ConnectionClass connectionClass;
    private  EditText sNo,FarmerName,FarmerAge;
    private Button submit;
    private Spinner villageSpinner,mandelSpinner;
    public TextView WARN_SNO,WARN_NAME,WARN_AGE;
    ProgressBar pbbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //connection sql
        connectionClass = new ConnectionClass();

        sNo=(EditText)findViewById(R.id.sno_edit);
        FarmerAge=(EditText)findViewById(R.id.farmer_age_edit);
        FarmerName  =(EditText)findViewById(R.id.farmer_name_edit);
        submit=(Button)findViewById(R.id.button_submit);
        villageSpinner=(Spinner) findViewById(R.id.village_spinner);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);

        mandelSpinner=(Spinner)findViewById(R.id.mandal_spinner);
        // text View casting
        WARN_SNO=(TextView)findViewById(R.id.warn_SNO);
        WARN_NAME=(TextView)findViewById(R.id.warn_Name);
        WARN_AGE=(TextView)findViewById(R.id.warn_Age);



       sNo.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(sNo.getText().toString().length() <  3){
                WARN_SNO.setVisibility(View.VISIBLE);
                WARN_SNO.setText("S.No. Must be 3 Digit long !");

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(sNo.getText().toString().length()==3){
                WARN_SNO.setVisibility(View.INVISIBLE);

            }

        }
    });
       FarmerAge.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(FarmerAge.getText().toString().matches("0") || FarmerAge.getText().toString().matches("00")) {

                   WARN_AGE.setVisibility(View.VISIBLE);
                   WARN_AGE.setText("ERROR ! Age can't be  0 ");


               }
           }

           @Override
           public void afterTextChanged(Editable s) {
               if(s.length() == 2  ){
                   WARN_AGE.setVisibility(View.INVISIBLE);
               }


           }
       });
       FarmerName.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(FarmerName.getText().toString().matches("(.*)[a-zA-z](.*)"))
               {

                   WARN_NAME.setVisibility(View.INVISIBLE);
                   // WARN_NAME.setText("Accept only alphabets");

               }


           }

           @Override
           public void afterTextChanged(Editable s) {
               if(FarmerName.getText().toString().matches("(.*)[0-9](.*)")
                       ){
                   WARN_NAME.setVisibility(View.VISIBLE);
                   WARN_NAME.setText("Accept only alphabets");

               }








           }
       });






       mandelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String sp1=String.valueOf(mandelSpinner.getSelectedItem());
               if(sp1.contentEquals("Mandal 1")){
                   List<String> list=new ArrayList<String>();
                   list.add("Village A");
                   list.add("Village B");
                   list.add("Village C");
                   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                           android.R.layout.simple_spinner_item, list);
                   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   dataAdapter.notifyDataSetChanged();
                   villageSpinner.setAdapter(dataAdapter);


               }

               if(sp1.contentEquals("Mandal 2")){
                   List<String> list=new ArrayList<String>();
                   list.add("Village D");
                   list.add("Village E");
                   list.add("Village F");
                   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                           android.R.layout.simple_spinner_item, list);
                   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   dataAdapter.notifyDataSetChanged();
                   villageSpinner.setAdapter(dataAdapter);


               }

               if(sp1.contentEquals("Mandal 3")){
                   List<String> list=new ArrayList<String>();
                   list.add("Village G");
                   list.add("Village H");
                   list.add("Village I");
                   ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,
                           android.R.layout.simple_spinner_item, list);
                   dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                   dataAdapter.notifyDataSetChanged();
                   villageSpinner.setAdapter(dataAdapter);


               }



           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });



        submit.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == submit){
                    AddPro addPro = new AddPro();
                    addPro.execute("");

                    sNo.setText("");
                    FarmerAge.setText("");
                    FarmerName.setText("");
                    WARN_SNO.setVisibility(View.INVISIBLE);
                    WARN_NAME.setVisibility(View.INVISIBLE);
                    WARN_AGE.setVisibility(View.INVISIBLE);
                }

            }
        });





    }


    public class AddPro extends AsyncTask<String, String, String> {



        String z = "";
        Boolean isSuccess = false;
       String Sno =sNo.getText().toString();
       String Name=FarmerName.getText().toString();
       String Age=FarmerAge.getText().toString();
        String Village=villageSpinner.getSelectedItem().toString();
        String Mandal=mandelSpinner.getSelectedItem().toString();




        @Override
        protected void onPreExecute() {
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            pbbar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();
            if(isSuccess==true) {
                Toast.makeText(MainActivity.this, "ADDED", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected String doInBackground(String... params) {
            if (Name.trim().equals("") || Sno.trim().equals(""))
                z = "Please Enter Value In Form";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {


                        String query = "insert into Form (Sno ,FarmerName ,FarmerAge ,Mandal ,Village)  values ('" + Sno + "','" + Name + "','" + Age + "','" + Mandal + "','" + Village + "' )";
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.executeUpdate();
                        z = "Added Successfully";
                        isSuccess = true;
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;
        }
    }








}


















