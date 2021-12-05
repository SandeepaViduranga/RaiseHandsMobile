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

    private EditText txtName, txtNIC, txtEmail, txtPhone, txtPassword, txtAge;
    private String Gender;
    private AlertDialog alertDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        txtName = (EditText) findViewById(R.id.txtname);
        txtEmail = (EditText) findViewById(R.id.txtemail);
        txtNIC = (EditText) findViewById(R.id.txtNIC);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtPassword = (EditText) findViewById(R.id.txtpassword);
        Gender = "null";
    }

    public void registration(View view) {
        String Name = txtName.getText().toString();
        String Email = txtEmail.getText().toString();
        String NIC = txtNIC.getText().toString();
        String Phone = txtPhone.getText().toString();
        String Age = txtAge.getText().toString();
        String Password = txtPassword.getText().toString();

        if (!(TextUtils.isEmpty(Name) && TextUtils.isEmpty(NIC) && TextUtils.isEmpty(Age) && TextUtils.isEmpty(Email) && TextUtils.isEmpty(Phone) && TextUtils.isEmpty(Password)  && TextUtils.isEmpty(NIC))) {
            if (validateNIC(NIC)) {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("type", "addMember");
                param.put("name", Name);
                param.put("email", Email);
                param.put("nic", NIC.toLowerCase().trim());
                param.put("age", Age);
                param.put("phone", Phone.trim());
                param.put("gender", Gender);
                param.put("Password", Password);
                //name email nic age phone gender Password
                Backgroundworker backgroundworker = new Backgroundworker(user_registration.this);
                backgroundworker.execute(param);
            } else {
                Toast.makeText(user_registration.this, "Invalide NIC ", Toast.LENGTH_SHORT).show();
            }

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
}