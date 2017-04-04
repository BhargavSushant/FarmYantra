package com.droid.atl.farmyantra;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends android.app.Fragment {

    View myView;
    DatabaseReference dbref;
    // firebase
    private FirebaseAuth firebaseAuth;
    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private EditText etCompany, etContactPerson, etContactNo;
    private ProgressDialog progressDialog;

    public signup() {
    }

    public static void hideKeyboard(boolean val, Activity activity) {
        View view;
        view = activity.getWindow().getCurrentFocus();
        if (val == true) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_signup, container, false);

        /*********************************************SIGN UP FIREBASE CODE*******************************************/

        //initializing firebase   object
        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference dbref;
        //initializing views
        editTextEmail = (EditText) myView.findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) myView.findViewById(R.id.editTextPassword);
        etCompany = (EditText) myView.findViewById(R.id.etCompany);
        etContactNo = (EditText) myView.findViewById(R.id.etContactNo);
        etContactPerson = (EditText) myView.findViewById(R.id.etContactPerson);

        buttonSignup = (Button) myView.findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(getActivity());

        /************************************************************************************************************/
        //attaching listener to button
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                hideKeyboard(true, getActivity());
            }
        });

        return myView;
    }

    private void registerUser() {
        //getting email and password from edit texts
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String company = etCompany.getText().toString().trim();
        final String contact = etContactNo.getText().toString().trim();
        final String contact_person = etContactPerson.getText().toString().trim();


        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            //Toast.makeText(getActivity(), "Successfully registered", Toast.LENGTH_LONG).show();
                            // if user created then also create it's database
                            updateUserDetails(email, company, contact_person, contact);
                            alertUser();
                            getActivity().getFragmentManager().popBackStack();
                            //getActivity().getFragmentManager().beginTransaction().remove(signup.this).commit();

                        } else {
                            //display some message here
                            Toast.makeText(getActivity(), "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void updateUserDetails(String email, String company, String contact_person, String contact) {

        FirebaseDatabase databaseRef = FirebaseDatabase.getInstance();
        FirebaseUser FarmYantra = firebaseAuth.getCurrentUser();
        String userId = FarmYantra.getUid();
        DatabaseReference dbref = databaseRef.getReference().child("FarmYantra").child("Users");
// create a custom userdetails object to pass on with setValue()
        UserDetails userdetails = new UserDetails(userId, email, company, contact_person, contact);
//remove all special characters to form a custom UID
        String company1 = company.replaceAll(" ", "");
        String emailUID = company1.replaceAll("[^[a-z]|^[0-9]]", "") + "BY" + email.replaceAll("[^[a-z]|^[0-9]]", "");
        dbref.child("UserProfile").child(emailUID).setValue(userdetails);
    }

    private void alertUser() {

        AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
        ad.setTitle("Alert Window");
        ad.setIcon(R.drawable.greencheckmark);
        ad.setMessage("Congratulations !" + "\n" + "you are successfully registered !! ");
        ad.setButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //ad is the object reference
        ad.show();
    }

}