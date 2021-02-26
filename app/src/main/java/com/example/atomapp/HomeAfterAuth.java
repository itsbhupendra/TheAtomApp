package com.example.atomapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class HomeAfterAuth extends AppCompatActivity {

   private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_after_auth);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        Toast.makeText(HomeAfterAuth.this,"Successfully Signed In",Toast.LENGTH_SHORT).show();

        /***
         * Retrieving the name of the Last Signed In Account user.
         */
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(signInAccount!=null){

            name = signInAccount.getDisplayName();
        }

        Button button=findViewById(R.id.action_logout_for_user);
        Button buttonDisplayName=findViewById(R.id.display_name_for_user);
        ImageView backButtonInHomeUser=findViewById(R.id.back_button_in_home_as_user);

        /***
         * Closing the Activity when back Button is pressed to avoid cycle
         * (HomeAfterAuth : Back button Pressed -> LoginActivity: Checks if user is Non- null -> HomeAfterAuth Activity)
         */
        backButtonInHomeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        buttonDisplayName.setText("Hi, "+name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /***
                 * Firebase SignOut.
                 */
                FirebaseAuth.getInstance().signOut();

                Intent intent=new Intent(HomeAfterAuth.this,LoginActivity.class);
                Toast.makeText(HomeAfterAuth.this,"Signed Out",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}