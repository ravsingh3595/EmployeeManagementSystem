package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;

public class LoginScreenActivity extends AppCompatActivity {


    private Button btnLogin;
    private EditText edtUserName;
    private EditText edtPassword;
    private CheckBox ckbRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        initView();

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
    }

    private void initView()
    {
        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        ckbRememberMe = (CheckBox)findViewById(R.id.ckbRememberMe);
    }

    public void signUp(View v){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }


}