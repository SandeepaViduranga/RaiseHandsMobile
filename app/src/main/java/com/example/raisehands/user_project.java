package com.example.raisehands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.raisehands.Modles.Project;

import java.io.Serializable;
import java.util.HashMap;

public class user_project extends AppCompatActivity {

    private Project project;
    private TextView txtProName,txtProOrg,txtProPecentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_project);

        txtProName  = (TextView) findViewById(R.id.txtProjectName);
        txtProOrg  = (TextView) findViewById(R.id.txtProjectOrgName);
        txtProPecentage  = (TextView) findViewById(R.id.txtProjectPercentage);

        if(getIntent().getExtras() != null) {
            project = (Project) getIntent().getSerializableExtra("ProjectObj");
            txtProName.setText(project.getProject_Name());
            txtProOrg.setText(project.getProject_Org_Name());
            txtProPecentage.setText(project.getPercentage());
        }
    }

    public void goToDonate(View view){
        Intent intent = new Intent(this, user_donate.class);
        intent.putExtra("PID",project.getProject_ID());
        intent.putExtra("ProjectObj", (Serializable) project);
        this.startActivity(intent);
    }
}