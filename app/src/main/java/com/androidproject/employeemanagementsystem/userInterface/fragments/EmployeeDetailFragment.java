package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.model.employee.Employee;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EmployeeDetailFragment extends Fragment {

    Employee employee;
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
//    @BindView(R.id.txt)
//    TextView txt;
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
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee_detail, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, rootView);

        /* get employee */
        /*
        Employee employee = new Employee();
        Bundle bundle = new Bundle();
        employee =  bundle.getParcelable("employee");
        */

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_employee_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_pdfSave:
                // download pdf code
                Toast.makeText(getActivity(), "download pdf", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void populateData(){

    }
}
