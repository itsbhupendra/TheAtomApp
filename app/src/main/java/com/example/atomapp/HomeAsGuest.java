package com.example.atomapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class HomeAsGuest extends AppCompatActivity {

    private static final String TAG = "GoogleActivity";

//    private String guestName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_as_guest);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

//        String temp=RegistrationActivity.

//        Bundle gt=getIntent().getExtras();
//        guestName=gt.getString("message");

//        String guestName=getIntent().getStringExtra("message");
//        String guestName1=getIntent().getStringExtra("message");
        Intent intent = getIntent();
        String guestName1 = intent.getStringExtra("message");

        Log.e("msg",guestName1);
        Toast.makeText(HomeAsGuest.this,guestName1,Toast.LENGTH_SHORT).show();
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


        buttonGuestName1.setText("Hi, "+guestName1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeAsGuest.this,LoginActivity.class);
                startActivity(intent);
            }
        });

//        Toast.makeText(HomeAsGuest.this, "Hi!", Toast.LENGTH_LONG).show();
//        ActionBar bar = getActionBar();
////        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ff")));
//        bar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//        ActionBar mActionBar=getActionBar();
//        mActionBar.setHomeButtonEnabled(true);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
//                | ActionBar.DISPLAY_SHOW_CUSTOM);
//        ImageView imageView = new ImageView(actionBar.getThemedContext());
//        imageView.setScaleType(ImageView.ScaleType.CENTER);
//        imageView.setImageResource(R.drawable.google);
//        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
//                | Gravity.CENTER_VERTICAL);
//        layoutParams.rightMargin = 40;
//        imageView.setLayoutParams(layoutParams);
//        actionBar.setCustomView(imageView);

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_editor.xml file.
//        // This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.menu_home_as_guest, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Save" menu option
//            case R.id.action_login:
//                // Do nothing for now
//                return true;
//            // Respond to a click on the "Delete" menu option
//
////                return super.onOptionsItemSelected(item);
//
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
}