package com.example.sleepmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    protected GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabase;
    private SignInButton signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set the dimensions of the sign-in button.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_sign_in);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setVisibility(View.VISIBLE);
        //try sign in if the button is clicked
        signInButton.setOnClickListener(this::onClick);
        //test
//        User user = new User();
//        String email = "test0@gmail.com";
//        DatabaseReference tDatabase = FirebaseDatabase.getInstance().getReference().child("users");
//
//        tDatabase.push();
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            signedIn(account);
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 0) {
            // The Task returned from this call is always completed, no need to attach a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            signedIn(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //return to initial page if sign in failed
            signedIn(null);
        }
    }
    protected void signedIn(final GoogleSignInAccount account) {
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.putExtra("googleAccount", account);
        startActivity(intent);
    }
    private void writeNewUser(final GoogleSignInAccount account) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String email = "test0@gmail.com";
        User user = new User();
        mDatabase.child("users").child(email).setValue(user);
        mDatabase.push();
    }
}