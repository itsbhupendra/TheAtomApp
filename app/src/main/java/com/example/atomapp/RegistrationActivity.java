package com.example.atomapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    public String guestName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        EditText usernameEditText= findViewById(R.id.edit_name);
        Button buttonContinue= findViewById(R.id.continueToHome);
        ImageView backButtonInRegistration=findViewById(R.id.back_button_in_Registration);


        guestName=usernameEditText.getText().toString();


        backButtonInRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /***
                 * Check if EditText field is empty or not.
                 * If yes,then show a Toast message to write some name in EditText field to continue.
                 */

                   if(TextUtils.isEmpty(usernameEditText.getText().toString())) {
                       Toast.makeText(RegistrationActivity.this, "Please type a name", Toast.LENGTH_LONG).show();
                       return;
                   }
                   else {
                       Intent intent = new Intent(RegistrationActivity.this, HomeAsGuest.class);
                       intent.putExtra("message",usernameEditText.getText().toString());
                       startActivity(intent);
//                       finish();
                   }

                }


        });

    }

}