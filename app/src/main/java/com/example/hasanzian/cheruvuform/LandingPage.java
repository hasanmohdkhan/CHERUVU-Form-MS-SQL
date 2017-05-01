package com.example.hasanzian.cheruvuform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hasanZian on 27-04-2017.
 */

public class LandingPage extends AppCompatActivity {
    private EditText mUserEdit,mPasswordEdit;
    private Button mLogin;
private TextView waringtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUserEdit=(EditText)findViewById(R.id.User_name_Edit);
        mPasswordEdit=(EditText)findViewById(R.id.Edit_Password);
        mLogin=(Button)findViewById(R.id.button_Login);
        waringtext=(TextView)findViewById(R.id.TextWraning);
        final Intent i=new Intent(this,MainActivity.class);
        mLogin.setEnabled(false);


        final TextWatcher checker= new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                  mLogin.setEnabled(false);



          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

              if(mPasswordEdit.getText().toString().length()==0 || mPasswordEdit.getText().toString().length()<6){
                  waringtext.setText("Passwords must contain at least six characters");

              }

              if (mPasswordEdit.getText().toString().length()==6){
                  waringtext.setVisibility(View.INVISIBLE);


              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              if(mUserEdit.getText().toString().length()!= 0 && mPasswordEdit.getText().toString().length()!=0 ){
                  mLogin.setEnabled(true);
              }




          }
      };
        mUserEdit.addTextChangedListener(checker);
        mPasswordEdit.addTextChangedListener(checker);







        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);

            }
        });






    }
}