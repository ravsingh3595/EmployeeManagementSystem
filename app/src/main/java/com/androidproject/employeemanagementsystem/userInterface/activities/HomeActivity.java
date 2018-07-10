package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.util.pdf.GenerateEmployeeDetailsPDF;

public class HomeActivity extends AppCompatActivity {

    Button alertCheckbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



//
//        DBHelper mDBHelper = new DBHelper(this);
//        SQLiteDatabase mSQLiteDatabase = mDBHelper.getWritableDatabase();

//        AlertMessageFragment frag = new AlertMessageFragment();
//        FragmentManager manager= getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.AlertCheck, frag, "thirdFragment");
//        transaction.commit();
    }
    public void showDialog(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
        alertDialogBuilder.setTitle("Alert Message");
        alertDialogBuilder.setMessage("Here we will show the details");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                showMessage("Cancel - no");
            }
        });

        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                showMessage("OK - Record Saved Successfully");
                dialogInterface.dismiss();
                // TODO
                // change the class to whereeve the final destination
                Intent mIntent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(mIntent);
            }
        });

        alertDialogBuilder.setNeutralButton("Ignore", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                showMessage("Ignore");
            }
        });

        AlertDialog mAlertDialog = alertDialogBuilder.create();
        mAlertDialog.show();
    }

    public void showMessage(String msg)
    {
        Toast toast = Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void generatePdf(View view){

        Employee employee = (Employee) getIntent().getSerializableExtra("employee");
        GenerateEmployeeDetailsPDF pdf = new GenerateEmployeeDetailsPDF();
        pdf.generateEmployeeDetailsPDF(employee);
        pdf.setBackToTopLink();
    }
}
