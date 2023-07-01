package com.example.raisehands;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.raisehands.BackgroundWorker.Backgroundworker;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class user_registration extends AppCompatActivity {

    private EditText txt_firstName, txt_lastName, txtJob, txtCountry, txtAddress, txtPhone, txtEmail, txtPassword;
    private String Gender;
    private AlertDialog alertDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        txt_firstName = (EditText) findViewById(R.id.first_name);
        txt_lastName = (EditText) findViewById(R.id.last_name);
        txtJob = (EditText) findViewById(R.id.job);
        txtCountry = (EditText) findViewById(R.id.country);
        txtAddress = (EditText) findViewById(R.id.address);
        txtPhone = (EditText) findViewById(R.id.mobile);
        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password_user);
        Gender = "null";
    }

    public void registration(View view) {
        String FirstName = txt_firstName.getText().toString();
        String LastName = txt_lastName.getText().toString();
        String Job = txtJob.getText().toString();
        String Country = txtCountry.getText().toString();
        String Address = txtAddress.getText().toString();
        String Phone = txtPhone.getText().toString();
        String Email = txtEmail.getText().toString();
        String Password = txtPassword.getText().toString();

        if (!(TextUtils.isEmpty(FirstName) && TextUtils.isEmpty(LastName) && TextUtils.isEmpty(Job) && TextUtils.isEmpty(Email) && TextUtils.isEmpty(Phone) && TextUtils.isEmpty(Password)  && TextUtils.isEmpty(Address))) {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("type", "addDonoor");
                param.put("first_name", FirstName);
                param.put("last_name", LastName);
                param.put("job", Job);
                param.put("country", Country);
                param.put("address", Address);
                param.put("mobile", Phone);
                param.put("email", Email);
                param.put("Password", Password);
                //name email nic age phone gender Password
                Backgroundworker backgroundworker = new Backgroundworker(user_registration.this);
                backgroundworker.execute(param);
            } else {
            Toast.makeText(user_registration.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayName(String result) {
        try {
            JSONObject jsonObj = new JSONObject(result);
            String status = jsonObj.getString("Status");
            if (status.equals("1")) {
                String LID = jsonObj.getString("LID");
                Intent intent = new Intent(this, MainActivity.class);
                String Extra_text1 = LID;
                intent.putExtra("Extra_text", Extra_text1);
                this.startActivity(intent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("testingerror", e.toString());
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("user_registration Status");
            String reg = "SQLSTATE[23000]";
            if (result.contains(reg)) {
                alertDialog.setMessage("NIC number exists !");
            } else {
                alertDialog.setMessage("Error");
            }
            alertDialog.show();
        }
    }

    public void goTo_Login(View view) {
        Intent intent = new Intent(user_registration.this, user_login.class);
        startActivity(intent);
    }
}