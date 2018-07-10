package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.FullTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.Intern;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.CommissionBasedPartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.FixedBasedPartTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeDetailActivity extends AppCompatActivity {

    @BindView(R.id.txtText)
    TextView txtText;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.linearTop)
    LinearLayout linearTop;
    @BindView(R.id.txtDobTitle)
    TextView txtDobTitle;
    @BindView(R.id.txtDob)
    TextView txtDob;
    @BindView(R.id.txtVehicalTitle)
    TextView txtVehicalTitle;
//    @BindView(R.id.txtVehical)
//    TextView txtVehical;
    @BindView(R.id.txtModelTitle)
    TextView txtModelTitle;
    @BindView(R.id.txtModel)
    TextView txtModel;
    @BindView(R.id.txtPlateNumberTitle)
    TextView txtPlateNumberTitle;
    @BindView(R.id.txtPlateNumber)
    TextView txtPlateNumber;
    @BindView(R.id.relativeVehical)
    RelativeLayout relativeVehical;
    @BindView(R.id.txtEmployeeType)
    TextView txtEmployeeType;
    @BindView(R.id.txtHourWorkedTitle)
    TextView txtHourWorkedTitle;
    @BindView(R.id.txtHourWorked)
    TextView txtHourWorked;
    @BindView(R.id.txtRateTitle)
    TextView txtRateTitle;
    @BindView(R.id.txtRate)
    TextView txtRate;
    @BindView(R.id.txtCommOrFixedTitle)
    TextView txtCommOrFixedTitle;
    @BindView(R.id.relativePartTime)
    RelativeLayout relativePartTime;
    @BindView(R.id.txtCollegeNameTitle)
    TextView txtCollegeNameTitle;
    @BindView(R.id.txtCollegeName)
    TextView txtCollegeName;
    @BindView(R.id.relativeIntern)
    RelativeLayout relativeIntern;
    @BindView(R.id.txtSalaryTitle)
    TextView txtSalaryTitle;
    @BindView(R.id.txtSalary)
    TextView txtSalary;
    @BindView(R.id.txtBonusTitle)
    TextView txtBonusTitle;
    @BindView(R.id.txtBonus)
    TextView txtBonus;
    @BindView(R.id.relativeFullTime)
    RelativeLayout relativeFullTime;
    @BindView(R.id.constraintLayout)
    RelativeLayout constraintLayout;
    @BindView(R.id.txtCommPerOrFixedAmount)
    TextView txtCommPerOrFixedAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        ButterKnife.bind(this);
        setTitle("Employee Detail");

        Employee employee = (Employee) getIntent().getSerializableExtra("employee");
        populateData(employee);


        relativeFullTime.setVisibility(View.GONE);
        relativeIntern.setVisibility(View.GONE);
        relativePartTime.setVisibility(View.GONE);
        relativeVehical.setVisibility(View.GONE);

        populateData(employee);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_employee_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pdfSave:
                // download pdf code
                Toast.makeText(EmployeeDetailActivity.this, "download pdf", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void populateData(Employee employee) {
//        txtDob.setText(employee.getAge());

        if (employee.getVehicle() != null) {
            txtModel.setText(employee.getVehicle().getCompany());
            txtPlateNumber.setText(employee.getVehicle().getPlate());
            relativeVehical.setVisibility(View.VISIBLE);
        } else {
            txtVehicalTitle.setText("Employee has no vehicle");

        }

        String empType = employee.getEmployee();
        txtName.setText(employee.getName());
        Log.e("emp type", empType);
        if (empType.equals("Intern")) {
            Intern intern = (Intern) employee;
            txtEmployeeType.setText("Employee is Intern");
            relativeIntern.setVisibility(View.VISIBLE);
            txtCollegeName.setText(intern.getSchoolName());
        } else if (empType == "FullTime") {
            FullTime fullTime = (FullTime) employee;
            txtEmployeeType.setText("Employee is Full Time");
            relativeFullTime.setVisibility(View.VISIBLE);
            txtSalary.setText(String.valueOf(fullTime.getSalary()));
            txtBonus.setText(String.valueOf(fullTime.getBonus()));
        } else if (empType == "Commission based") {
            CommissionBasedPartTime commissionBasedPartTime = (CommissionBasedPartTime) employee;
            txtEmployeeType.setText("Employee is Commission based");
            txtHourWorked.setText(String.valueOf(commissionBasedPartTime.getHoursWorked()));
            txtRate.setText(String.valueOf(commissionBasedPartTime.getRate()));
            txtCommOrFixedTitle.setText("Commition Percentage: ");
            txtCommPerOrFixedAmount.setText((String.valueOf(commissionBasedPartTime.getCommissionPercentage())) + "%");
            relativePartTime.setVisibility(View.VISIBLE);

        } else if (empType == "Fixed based") {
            FixedBasedPartTime fixedBasedPartTime = (FixedBasedPartTime) employee;
            txtEmployeeType.setText("Employee is Fixed based");
            relativePartTime.setVisibility(View.VISIBLE);
            txtCommOrFixedTitle.setText("Fixed Amount: ");
            txtCommPerOrFixedAmount.setText(String.valueOf(fixedBasedPartTime.getFixedAmount()));

            txtHourWorked.setText(String.valueOf(fixedBasedPartTime.getHoursWorked()));
            txtRate.setText(String.valueOf(fixedBasedPartTime.getRate()));
        }

    }

}
