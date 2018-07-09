package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.db.DBUser;
import com.androidproject.employeemanagementsystem.model.user.User;
import com.androidproject.employeemanagementsystem.util.pdf.Utility;

public class SignUpActivity extends AppCompatActivity {

    private Button btnSignUp;
    private EditText edtFullname;
    private EditText edtPassword;
    private EditText edtEmail;
    private EditText edtConfirmPassword;

    User user = new User();
    DBUser dbUser = new DBUser(SignUpActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    Log.d("DBUser outside", String.valueOf(validate()));
                    Log.d("DBUser inside", String.valueOf(isEmailValid(edtEmail.getText().toString())));

//                    if(isEmailValid(edtEmail.getText().toString()))
//                    {
                        Log.d("DBUser inside", String.valueOf(isEmailValid(edtEmail.getText().toString())));
                        user.setFullname(edtFullname.getText().toString());
                        user.setEmail(edtEmail.getText().toString());
                        user.setPassword(edtPassword.getText().toString());
                        dbUser.insertUser(user);
                        dbUser.getAllUser();
                        Intent i = new Intent(SignUpActivity.this, LoginScreenActivity.class);
                        startActivity(i);
                        finish();
//                    }
//                    else {
//                        Toast.makeText(SignUpActivity.this, "Invalid Email Id, Please try again later", Toast.LENGTH_LONG).show();
//                    }
                }
//                else {
//                    Toast.makeText(SignUpActivity.this, "Password Didn't match, Please try again later", Toast.LENGTH_LONG).show();
//                }

            }
        });
    }

    boolean isEmailValid(CharSequence email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    /*
    public boolean validate()
    {

        if(edtPassword.getText().toString().length() != 0)
        {
            if(edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString()))
            {
                return true;
            }
            else
            {
                edtConfirmPassword.setError("Confirm Password not matched");
            }
        }
        else
        {
            edtPassword.setError("Enter Password");
        }

        return false;

    }
    */
    public boolean validate() {
       if (edtFullname.getText().toString().isEmpty()) {
           edtFullname.setError("Please enter full name");
       } else if (!Utility.isValidEmail(edtEmail.getText())) {
           edtEmail.setError("Please enter a valid email address");
       } else if (edtPassword.getText().toString().isEmpty()) {
           edtPassword.setError("Please Enter Password");
       } else if (edtPassword.getText().toString().length() < 5) {
           edtPassword.setError("Please enter password greater than 5 characters");
       }
       if (edtPassword.getText().toString().length() != 0) {
           if (edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
               return true;
           } else {
               edtConfirmPassword.setError("Confirm Password not matched");
           }
       }
       return false;

   }

    private void initView()
    {
        edtFullname = (EditText)findViewById(R.id.edtFullname);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtConfirmPassword = (EditText)findViewById(R.id.edtConfirmPassword);
    }
}
