package com.example.atomapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    public String guestName="Anon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
//        assert getSupportActionBar() != null;   //null check
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Bundle basket=new Bundle();
//        basket.putString("abc",guestName);

        EditText usernameEditText= findViewById(R.id.edit_name);
        Button buttonContinue=(Button) findViewById(R.id.continueToHome);
        Button backButtonInRegistration=findViewById(R.id.back_button_in_Registration);


        guestName=usernameEditText.getText().toString();


//        String GuestName=findViewById(R.id.edit_name).toString();

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

//                if(GuestName.matches("")){
//                    Toast.makeText(RegistrationActivity.this,"Please type a name",Toast.LENGTH_LONG).show();
////            Toast.makeText(getActivity(), "This is my Toast message!",
////                    Toast.LENGTH_LONG).show();
//                }


//                    Intent intent = new Intent(RegistrationActivity.this,HomeAsGuest.class);
//                startActivity(intent);
//                Log.e(guestName);
                   if(TextUtils.isEmpty(usernameEditText.getText().toString())) {
//                       Log.e(GuestName.toString());
                       Toast.makeText(RegistrationActivity.this, "Please type a name", Toast.LENGTH_LONG).show();
                       return;
                   }
                   else {
                       Intent intent = new Intent(RegistrationActivity.this, HomeAsGuest.class);
                       intent.putExtra("message",usernameEditText.getText().toString());
                       startActivity(intent);
                   }
////
////
                }


        });

//        if(GuestName.length()==0){
//            Toast.makeText(this,"Please type a name",Toast.LENGTH_LONG).show();
////            Toast.makeText(getActivity(), "This is my Toast message!",
////                    Toast.LENGTH_LONG).show();
//        }
    }

//    private String getGuestName() {
//        EditText usernameEditText= findViewById(R.id.edit_name);
//        String tempGuestName=usernameEditText.getText().toString();
//        return tempGuestName;
//    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        finish();
//        return true;
//    }
}