package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidproject.employeemanagementsystem.R;


public class SignUpActivity extends AppCompatActivity {

    private Button btnSignup;
    private EditText edtUserName;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, LoginScreenActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initView()
    {
        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnSignup = (Button)findViewById(R.id.btnSignUp);

    }
}
