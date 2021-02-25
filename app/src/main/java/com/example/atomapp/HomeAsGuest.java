package com.example.atomapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class HomeAsGuest extends AppCompatActivity {

    private static final String TAG = "GoogleActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_as_guest);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        String guestName = intent.getStringExtra("message");



        Button buttonGuestName1=findViewById(R.id.display_name_for_guest);
        Button button= findViewById(R.id.action_login_for_guest);
        Button backButtonInHomeAsGuest=findViewById(R.id.back_button_in_home_as_guest);


        backButtonInHomeAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAsGuest.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });


        buttonGuestName1.setText("Hi, "+guestName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeAsGuest.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

}