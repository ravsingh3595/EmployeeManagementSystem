package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.db.DBUser;
import com.androidproject.employeemanagementsystem.model.user.User;

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
                    user.setFullname(edtFullname.getText().toString());
                    user.setEmail(edtEmail.getText().toString());
                    user.setPassword(edtPassword.getText().toString());
                    dbUser.insertUser(user);
                    dbUser.getAllUser();

                }
                Toast.makeText(SignUpActivity.this, "Incorrect Password, Please try again later", Toast.LENGTH_LONG).show();

                Intent i = new Intent(SignUpActivity.this, LoginScreenActivity.class);
                startActivity(i);
                finish();


            }
        });
    }


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
    private void initView()
    {
        edtFullname = (EditText)findViewById(R.id.edtFullname);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtConfirmPassword = (EditText)findViewById(R.id.edtConfirmPassword);
    }
}
