package com.example.raisehands;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.raisehands.BackgroundWorker.Backgroundworker;
import com.example.raisehands.Modles.Project;
import com.example.raisehands.RecycleView.Recycleview_Project_config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class user_dashboard extends AppCompatActivity {

    private List<Project> dataList;
    private List<String> dataListID;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        dataList = new ArrayList<Project>();
        dataListID = new ArrayList<String>();
        recyclerView = (RecyclerView) findViewById(R.id.AdminOrderRecyclerView);
    }

    private void loadProjects(){
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("type", "load_Records");
        Backgroundworker backgroundworker = new Backgroundworker(user_dashboard.this);
        backgroundworker.execute(param);
    }

    public void displayName(String result) {
        dataList.clear();
        dataListID.clear();
        Log.i("result",result);

        try {
            JSONArray jsonArray = new JSONArray(result.trim());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Project project = new Project();
                //`Project_ID`, `Project_Name`, `Project_Org_Name`, `Project_Manager`, `Org_Email`, `Project_Country`, `Project_City`, `Project_Address`, `Project_Type`, `Project_Start_Date`, `Project_End_Date`, `Project_EstAmount`, `Project_CrtAmount`, `Image`, `Document_1`, `Document_2`, `Document_3`, `Project_description`, `Project_Status`, `Percentage`, `Organization_ID`
                String Pid = jsonObject.getString("Project_ID");
                project.setProject_ID(jsonObject.getString("Project_ID"));
                project.setProject_Name(jsonObject.getString("Project_Name"));
                project.setProject_Org_Name(jsonObject.getString("Project_Org_Name"));
                project.setPercentage(jsonObject.getString("Percentage"));
                project.setImage(jsonObject.getString("Image"));

                dataListID.add(Pid);
                dataList.add(project);
            }

            if (dataListID.isEmpty()) {
                Toast.makeText(this, "No Records Available !", Toast.LENGTH_SHORT).show();
            } else {
                new Recycleview_Project_config().setConfig(recyclerView, user_dashboard.this, dataList, dataListID);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("testingerror", e.toString());
        }
    }
}