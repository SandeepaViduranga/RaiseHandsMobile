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

public class user_login extends AppCompatActivity {

    private AlertDialog alertDialog;
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password_user);
    }

    public void goRegister(View view) {
        Intent intent = new Intent(user_login.this, MainActivity.class);     //redirects to next page
        startActivity(intent);
        this.finish();
    }

    private void login(String userName, String userPassword) {
        if (!(TextUtils.isEmpty(userName) && TextUtils.isEmpty(userPassword))) {
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("type", "login");
            param.put("Username", userName);
            param.put("Password", userPassword);
            Backgroundworker backgroundworker = new Backgroundworker(user_login.this);
            backgroundworker.execute(param);
        } else {
            Toast.makeText(user_login.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayName(String result) {
        Log.i("Error_Check",result);
        try {
            JSONObject jsonObj = new JSONObject(result);
            String status = jsonObj.getString("Status");

            if (status.equals("1")) {
                String LID = jsonObj.getString("LID");
                Intent intent = new Intent(this, user_dashboard.class);
                String Extra_text1 = LID;
                intent.putExtra("Extra_text", Extra_text1);
                this.startActivity(intent);
            } else {
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage("Invalid Credintials");
                alertDialog.show();
            }
        } catch (JSONException e) {
            Log.i("Error_Check",result);
            e.printStackTrace();
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Login Status");
            alertDialog.setMessage("Error");
            alertDialog.show();
        }
    }
}