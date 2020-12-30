package com.dumichelle.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth auth;
    FragmentTransaction transaction;

    Button emailSignInButton, emailCreateAccountButton, signOutButton;
    EditText password, username;
    String myusername;
    private static final String TAG = "EmailPassword";
    private static Button defaultFragment;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailSignInButton = findViewById(R.id.sign_in);
        emailCreateAccountButton = findViewById(R.id.sign_up);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        emailSignInButton.setOnClickListener(this);
        emailCreateAccountButton.setOnClickListener(this);
        myusername = username.getText().toString();

        //fragmentManager = getSupportFragmentManager();//Get Fragment Manager


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    public void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            //go to the other screen
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = username.getText().toString();
        if (TextUtils.isEmpty(email)) {
            username.setError("Required.");
            valid = false;
        } else {
            username.setError(null);
        }

        String password_ = password.getText().toString();
        if (TextUtils.isEmpty(password_)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }

    private void signIn(String email, String password) {

        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "Authentication worked.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);

                            /*Intent intent = new Intent(getApplicationContext(), Activity2.class);
                            intent.putExtra("username", username.getText().toString());
                            startActivity(intent);*/
                            /*Bundle bundle = new Bundle();
                            bundle.putString("username", username.getText().toString());
                            // set Fragmentclass Arguments
                            HomeFragment fragmentobj = new HomeFragment();
                            fragmentobj.setArguments(bundle);*/

                            Toast.makeText(MainActivity.this, "Authentication worked.",
                                    Toast.LENGTH_SHORT).show();

                            /*Bundle bundle = new Bundle();
                            String myMessage = "Stack Overflow is cool!";
                            bundle.putString("message", username.getText().toString() );
                            HomeFragment fragInfo = new HomeFragment();
                            fragInfo.setArguments(bundle);
                            transaction.replace(R.id.textView3, fragInfo);
                            transaction.commit();*/

                            /*myusername = username.getText().toString();
                            Bundle bundle = new Bundle();
                            bundle.putString("username", myusername);
                            HomeFragment fragInfo = new HomeFragment();
                            fragInfo.setArguments(bundle);*/

                            /*Bundle bundle = new Bundle();
                            bundle.putString("username", username.getText().toString());
                            HomeFragment myObj = new HomeFragment();
                            myObj.setArguments(bundle);*/

                            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                            intent.putExtra("username", username.getText().toString());
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }
                        if (!task.isSuccessful()) {
                            signOut();
                        }
                    }
                });
    }


    private void signOut() {
        auth.signOut();
        updateUI(null);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_up) {
            createAccount(username.getText().toString(), password.getText().toString());
        } else {
            signIn(username.getText().toString(), password.getText().toString());
        }
    }

}