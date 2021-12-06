package com.example.raisehands;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raisehands.BackgroundWorker.Backgroundworker;

import java.util.HashMap;

public class user_donate extends AppCompatActivity {
    private EditText txt_fullname, txt_email, txt_Namecard, txtAddress, txtCardnum, txtCity, txtExpmonth, txt_project_ID, txt_Donor_ID, txtState, txtZip, txt_Expyear, txt_CVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_donate);

        txt_fullname = (EditText) findViewById(R.id.full_name);
        txt_email = (EditText) findViewById(R.id.email);
        txt_Namecard = (EditText) findViewById(R.id.name_on_card);
        txtAddress = (EditText) findViewById(R.id.address);
        txtCardnum = (EditText) findViewById(R.id.card_number);
        txtCity = (EditText) findViewById(R.id.city);
        txtExpmonth = (EditText) findViewById(R.id.exp_month);
        txt_project_ID = (EditText) findViewById(R.id.project_id);
        txt_Donor_ID = (EditText) findViewById(R.id.donor_id);
        txtState = (EditText) findViewById(R.id.state);
        txtZip = (EditText) findViewById(R.id.zip_code);
        txt_Expyear = (EditText) findViewById(R.id.exp_year);
        txt_CVV = (EditText) findViewById(R.id.password_user);
    }

    public void registration(View view) {
        String FullName = txt_fullname.getText().toString();
        String Email = txt_email.getText().toString();
        String Name_on_card = txt_Namecard.getText().toString();
        String Address = txtAddress.getText().toString();
        String Card_number = txtCardnum.getText().toString();
        String City = txtCity.getText().toString();
        String Exp_month = txtExpmonth.getText().toString();
        String Project_ID = txt_project_ID.getText().toString();
        String Donor_ID = txt_Donor_ID.getText().toString();
        String State = txtState.getText().toString();
        String Zip_code = txtZip.getText().toString();
        String ExpYear = txt_Expyear.getText().toString();
        String CVV = txt_CVV.getText().toString();

        if (!(TextUtils.isEmpty(FullName) && TextUtils.isEmpty(Email) && TextUtils.isEmpty(Project_ID) && TextUtils.isEmpty(Email) && TextUtils.isEmpty(Donor_ID) && TextUtils.isEmpty(Address))) {
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("type", "addDonation");
            param.put("FullName", FullName);
            param.put("Project_ID", Project_ID);
            param.put("Donor_ID", Donor_ID);
            //name email nic age phone gender Password
            Backgroundworker backgroundworker = new Backgroundworker(user_donate.this);
            backgroundworker.execute(param);
        } else {
            Toast.makeText(user_donate.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayName(String result) {
    }
}