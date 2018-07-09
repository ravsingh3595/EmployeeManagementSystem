package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.androidproject.employeemanagementsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */

public class AddEmployeeFragment extends Fragment {


    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtDob)
    EditText edtDob;
    @BindView(R.id.chkVehicle)
    CheckBox chkVehicle;
    @BindView(R.id.rbCar)
    RadioButton rbCar;
    @BindView(R.id.rbMotorcycle)
    RadioButton rbMotorcycle;
    @BindView(R.id.rgbVehicle)
    RadioGroup rgbVehicle;
    @BindView(R.id.linearVehicle)
    LinearLayout linearVehicle;
    @BindView(R.id.imgModel)
    ImageView imgModel;
    @BindView(R.id.edtModel)
    EditText edtModel;
    @BindView(R.id.edtplate)
    EditText edtplate;
    @BindView(R.id.relativeVehicleInfo)
    RelativeLayout relativeVehicleInfo;
    @BindView(R.id.rbParttime)
    RadioButton rbParttime;
    @BindView(R.id.rbIntern)
    RadioButton rbIntern;
    @BindView(R.id.rbFulltime)
    RadioButton rbFulltime;
    @BindView(R.id.rgbEmployeeType)
    RadioGroup rgbEmployeeType;
    @BindView(R.id.linearEmpType)
    LinearLayout linearEmpType;
    @BindView(R.id.edtHours)
    EditText edtHours;
    @BindView(R.id.edtRate)
    EditText edtRate;
    @BindView(R.id.chkFixedOrCommission)
    CheckBox chkFixedOrCommission;
    @BindView(R.id.edtCommissionPerOrFixedAmt)
    EditText edtCommissionPerOrFixedAmt;
    @BindView(R.id.linearFixedOrCommission)
    LinearLayout linearFixedOrCommission;
    @BindView(R.id.linearParttime)
    LinearLayout linearParttime;
    @BindView(R.id.edtSchoolName)
    EditText edtSchoolName;
    @BindView(R.id.linearIntern)
    LinearLayout linearIntern;
    @BindView(R.id.edtSalary)
    EditText edtSalary;
    @BindView(R.id.edtBonus)
    EditText edtBonus;
    @BindView(R.id.linearFulltime)
    LinearLayout linearFulltime;
    @BindView(R.id.btnSavePayroll)
    Button btnSavePayroll;
    @BindView(R.id.linearMain)
    LinearLayout linearMain;
    Unbinder unbinder;

    public AddEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);
        unbinder = ButterKnife.bind(this, view);
        linearVehicle.setVisibility(View.GONE);
        relativeVehicleInfo.setVisibility(View.GONE);
        //linearEmpType.setVisibility(View.GONE);
        linearParttime.setVisibility(View.GONE);
        linearIntern.setVisibility(View.GONE);
        linearFulltime.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnSavePayroll)
    public void onViewClicked() {
    }

    @OnClick(R.id.chkVehicle)
    public void onChkVehicleClicked()
    {
        rgbEmployeeType.setVisibility(View.VISIBLE);
        if (chkVehicle.isChecked())
        {
            linearVehicle.setVisibility(View.VISIBLE);
            relativeVehicleInfo.setVisibility(View.VISIBLE);
        }
        else
        {
            linearVehicle.setVisibility(View.GONE);
            relativeVehicleInfo.setVisibility(View.GONE);
        }
    }

    @OnCheckedChanged({R.id.rbParttime, R.id.rbIntern, R.id.rbFulltime})
    public void onEmployeeTypeChanged(CompoundButton radioButton, boolean checked)
    {
        //boolean checked = radioButton.isChecked();

        if (checked)
        {
            linearParttime.setVisibility(View.GONE);
            linearIntern.setVisibility(View.GONE);
            linearFulltime.setVisibility(View.GONE);
            switch (radioButton.getId())
            {
                case R.id.rbParttime:
                    linearParttime.setVisibility(View.VISIBLE);

                    break;

                case R.id.rbIntern:
                    linearIntern.setVisibility(View.VISIBLE);
                    break;

                case R.id.rbFulltime:
                    linearFulltime.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @OnClick(R.id.chkFixedOrCommission)
    public void onChkFixedOrCommissionChecked()
    {
        if (chkFixedOrCommission.isChecked())
        {
            edtCommissionPerOrFixedAmt.setHint("Enter Fixed Commission Percentage");
        }
        else
        {
            edtCommissionPerOrFixedAmt.setHint("Enter Fixed Commission Amount");
        }
    }
}
