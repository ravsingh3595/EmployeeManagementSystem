package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.db.DBUser;

public class LoginScreenActivity extends AppCompatActivity {


    private Button btnLogin;
    private Button btnSignUp;
    private EditText edtEmail;
    private EditText edtPassword;
    private CheckBox ckbRememberMe;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        initView();

        //1 - Create Shared Preferences Object
        mSharedPreferences = getSharedPreferences(getString(R.string.Preferences),MODE_PRIVATE);
        final SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        //2 - Get saved values from shared preferences
        String userId = mSharedPreferences.getString("userId",null);
        String pwd = mSharedPreferences.getString("password",null);

        //3 - Set values to Edit text
        if(userId != null && pwd != null) {
            edtEmail.setText(userId);
            edtPassword.setText(pwd);
            ckbRememberMe.setChecked(true);
        }else
        {
            ckbRememberMe.setChecked(false);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(edtEmail.getText()) || edtEmail.getText().toString().length() == 0)
                {
                    edtEmail.setError("Please Enter User Name");
                }
                else {

                    DBUser dbUser = new DBUser(LoginScreenActivity.this);
                    if (dbUser.isValidUser(edtEmail.getText().toString(), edtPassword.getText().toString()))
                    {
                        Toast.makeText(LoginScreenActivity.this, "User Successfully logged In ", Toast.LENGTH_LONG).show();

                        if(ckbRememberMe.isChecked())
                        {
                            //5 - Save value to Shared Preferences using editor object
                            mEditor.putString("userId", edtEmail.getText().toString());
                            mEditor.putString("password", edtPassword.getText().toString());
                        }
                        else
                        {
                            //6 - Remove values from shared preferences
                            mEditor.remove("userId");
                            mEditor.remove("password");
                        }
                        //7 - Save changes permanently to shared preferences
                        mEditor.apply();

                        Intent intent = new Intent(LoginScreenActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        Toast.makeText(LoginScreenActivity.this, "UserID/password invalid", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginScreenActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initView()
    {
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        ckbRememberMe = (CheckBox)findViewById(R.id.ckbRememberMe);
    }
//
//
//    public void signUp(View v){
//        Intent i = new Intent(this, SignUpActivity.class);
//        startActivity(i);
//        finish();
//    }


}

/*
SharedPreferences mSharedPreferences = this.getSharedPreferences(getString(R.string.Preferences), MODE_PRIVATE );
        final SharedPreferences.Editor mEditor = mSharedPreferences.edit();

        if (mSharedPreferences.getString(getString(R.string.username), null) != null){
            edtUserName.setText(mSharedPreferences.getString(getString(R.string.username), "admin"));
            edtPassword.setText(mSharedPreferences.getString(getString(R.string.password), "s3cr3t"));
            ckbRememberMe.setChecked(true);

        }
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                if (userName.equals("admin") && password.equals("s3cr3t")) {

                    if (ckbRememberMe.isChecked()) {
                        mEditor.putString(getString(R.string.username), "admin");
                        mEditor.putString((getString(R.string.password)), "s3cr3t");
                    }
                    else
                    {
                        mEditor.remove(getString(R.string.username));
                        mEditor.remove(getString(R.string.password));
                        //clear all
                        //mEditor.clear();
                    }
                    mEditor.apply();

                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(mIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Login or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

 */