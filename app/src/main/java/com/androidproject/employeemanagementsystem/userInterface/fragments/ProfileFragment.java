package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.util.pdf.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ProfileFragment extends Fragment {

    @BindView(R.id.imgProfile)
    ImageView imgProfile;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.layoutName)
    TextInputLayout layoutName;
    @BindView(R.id.edtDOB)
    EditText edtDOB;
    @BindView(R.id.layoutDOB)
    TextInputLayout layoutDOB;
    @BindView(R.id.btnSelectDate)
    Button btnSelectDate;
    @BindView(R.id.linearDob)
    LinearLayout linearDob;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioButton)
    RadioButton radioButton;
    @BindView(R.id.rgGender)
    RadioGroup rgGender;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.input_layout_address)
    TextInputLayout inputLayoutAddress;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.btnUpload)
    Button btnUpload;
    @BindView(R.id.constraintLayout)
    RelativeLayout constraintLayout;
    Unbinder unbinder;
    @BindView(R.id.btnShowLoc)
    Button btnShowLoc;

    // variables and constants
    private DatePickerDialog datePickerDialog;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);


        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    /* handle button clicks */
    @OnClick({R.id.btnSelectDate, R.id.btnSave, R.id.btnUpload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSelectDate:
                selectDateButtonTapped();
                break;
            case R.id.btnSave:
                break;
            case R.id.btnUpload:
                selectImage();
                break;
        }
    }

    @OnClick(R.id.btnShowLoc)
    public void onViewClicked() {
        String url = "geo:0,0?q=Brooklyn+Bridge,New+York,NY";

        Uri uri = Uri.parse(url);
        showMap(uri);
    }


    /* handle choose date from calendar */

    public void selectDateButtonTapped() {

        // calender class's instance and get current date , month and year from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // set day of month , month and year value in the edit text
                edtDOB.setText(day + "/"
                        + (month + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    /* select Image and camera methods */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Log.e("Gellery", "Gellery INtent called");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Log.e("Camera", "Camera INtent called");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Log.e("onCaptureImageResult", "onCaptureImageResult called");
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imgProfile.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Log.e("onSelectFromGalleryResult ", "onSelectFromGalleryResult called");
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imgProfile.setImageBitmap(bm);
    }


    /* methods show map */
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}

