package com.example.raisehands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raisehands.BackgroundWorker.Backgroundworker;
import com.example.raisehands.Modles.Project;

import java.util.HashMap;

public class user_donate extends AppCompatActivity {
    private EditText txt_fullname, txt_email, txt_Amount, txtAddress, txtCardnum, txtCity, txtExpmonth, txtState, txtZip, txt_Expyear, txt_CVV;
    private Project project;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_donate);

        if(getIntent().getExtras() != null) {
            project = (Project) getIntent().getSerializableExtra("ProjectObj");
        }

        txt_fullname = (EditText) findViewById(R.id.full_name);
        txt_email = (EditText) findViewById(R.id.email);
        txt_Amount = (EditText) findViewById(R.id.name_on_card);
        txtAddress = (EditText) findViewById(R.id.address);
        txtCardnum = (EditText) findViewById(R.id.card_number);
        txtCity = (EditText) findViewById(R.id.city);
        txtExpmonth = (EditText) findViewById(R.id.exp_month);
        txtState = (EditText) findViewById(R.id.state);
        txtZip = (EditText) findViewById(R.id.zip_code);
        txt_Expyear = (EditText) findViewById(R.id.exp_year);
        txt_CVV = (EditText) findViewById(R.id.password_user);
    }

    public void makeADonation(View view) {
        String FullName = txt_fullname.getText().toString();
        String Email = txt_email.getText().toString();
        String Amount = txt_Amount.getText().toString();
        String Address = txtAddress.getText().toString();
        String Card_number = txtCardnum.getText().toString();
        String City = txtCity.getText().toString();
        String Exp_month = txtExpmonth.getText().toString();

        if (!(TextUtils.isEmpty(FullName) && TextUtils.isEmpty(Email) && TextUtils.isEmpty(Amount) && TextUtils.isEmpty(Amount) && TextUtils.isEmpty(Address))) {
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("type", "addDonation");
            param.put("FullName", FullName);
            param.put("Project_ID", project.getProject_ID());
            param.put("Donor_ID", "11");
            param.put("Amount",Amount);
            //name email nic age phone gender Password
            Backgroundworker backgroundworker = new Backgroundworker(user_donate.this);
            backgroundworker.execute(param);
        } else {
            Toast.makeText(user_donate.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayName(String result) {
        Log.i("result",result);
        Toast.makeText(user_donate.this, "Donation Complete!", Toast.LENGTH_SHORT).show();
    }
}