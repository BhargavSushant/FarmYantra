package com.droid.atl.farmyantra;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.view.View.GONE;


/**
 * A simple {@link Fragment} subclass.
 */
public class login extends android.app.Fragment {

    View myView;
    Context context;
    LinearLayout welcome_sublayout, login_sublayout;
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnLogin, return_to_products;

    public login() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_login, container, false);

        inputEmail = (EditText) myView.findViewById(R.id.email);
        inputPassword = (EditText) myView.findViewById(R.id.password);
        progressBar = (ProgressBar) myView.findViewById(R.id.progressBar);
        btnLogin = (Button) myView.findViewById(R.id.btn_login);
        return_to_products = (Button) myView.findViewById(R.id.return_to_products);
        welcome_sublayout = (LinearLayout) myView.findViewById(R.id.welcome_sublayout);
        login_sublayout = (LinearLayout) myView.findViewById(R.id.login_sublayout);
        context = getContext();

        welcome_sublayout.setVisibility(GONE);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(context, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(context, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show();
                            managePostLoginScreen();
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


            } // end signInWithEmailAndPassword

        }); // end button.setOnClickListener

        return_to_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStack();

            }
        });

        return myView;
    } // end onCreateView

    private void managePostLoginScreen() {

        login_sublayout.setVisibility(GONE);
        welcome_sublayout.setVisibility(View.VISIBLE);

    }
}// end class  login