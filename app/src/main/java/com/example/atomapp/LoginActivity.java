package com.example.atomapp;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "GoogleActivity";
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private ProgressBar mProgressbar;
    private static final String url="https://www.theatom.app/";



    @Override
    protected void onStart() {
        super.onStart();

        //Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser!=null){
            Intent intent = new Intent(LoginActivity.this,HomeAfterAuth.class);
            startActivity(intent);
//            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        mProgressbar=findViewById(R.id.progressBar);

        mProgressbar.setVisibility(ProgressBar.INVISIBLE);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        createRequest();   //Function to configure Google Sign In options

        Button buttonAsGuest=(Button) findViewById(R.id.continueAsGuest);
        TextView termsAndConditions=findViewById(R.id.terms_and_condition);

        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri termsConditionUri=Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW,termsConditionUri);
                startActivity(intent);
            }
        });

        buttonAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button buttonAsUser=(Button) findViewById(R.id.continueWithGoogle);

        buttonAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mProgressbar.setVisibility(ProgressBar.VISIBLE);
                signIn();
//                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...)
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            // Google Sign In was successful, authenticate with Firebase
            try {
                GoogleSignInAccount account=task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            }

            catch (ApiException e){
          // Google Sign In failed, show a message about the failure
                mProgressbar.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credentials = GoogleAuthProvider.getCredential(idToken,null);

        mAuth.signInWithCredential(credentials).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // If Sign In is successful start the Home Activity.
                if(task.isSuccessful()){

                    FirebaseUser user = mAuth.getCurrentUser();
                    mProgressbar.setVisibility(ProgressBar.INVISIBLE);
                    Intent intent=new Intent(getApplicationContext(),HomeAfterAuth.class);
                    startActivity(intent);
                }

                // If Sign In failed, display a message about the failure.
                else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void createRequest() {


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}