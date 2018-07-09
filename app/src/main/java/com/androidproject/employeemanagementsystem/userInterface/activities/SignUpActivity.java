package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.db.DBUser;
import com.androidproject.employeemanagementsystem.model.user.User;
import com.androidproject.employeemanagementsystem.util.pdf.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.lblSignUp)
    TextView lblSignUp;
    @BindView(R.id.edtFullname)
    EditText edtFullname;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.btnLogin)
    Button btnLogin;


    User user = new User();
    DBUser dbUser = new DBUser(SignUpActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        initView();

        // hide action bar
        getSupportActionBar().hide();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    user.setFullname(edtFullname.getText().toString());
                    user.setEmail(edtEmail.getText().toString());
                    user.setPassword(edtPassword.getText().toString());
                    dbUser.insertUser(user);
                    dbUser.getAllUser();
                    goToLogin();
                }
//                Toast.makeText(SignUpActivity.this, "Incorrect Password, Please try again later", Toast.LENGTH_LONG).show();
//
//                Intent i = new Intent(SignUpActivity.this, LoginScreenActivity.class);
//                startActivity(i);
//                finish();

            }
        });
    }


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

    private void initView() {
        edtFullname = (EditText) findViewById(R.id.edtFullname);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
    }

    private void goToLogin(){
        Intent i = new Intent(SignUpActivity.this, LoginScreenActivity.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        goToLogin();
    }
}
