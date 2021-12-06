package com.example.raisehands.RecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.raisehands.Modles.Project;
import com.example.raisehands.R;
import com.example.raisehands.user_project;

import java.io.Serializable;
import java.util.List;

public class Recycleview_Project_config {
    private Context mContext;
    private ProjectAddaptor mProjectAddaptor;


    public void setConfig(RecyclerView recyclerView, Context context, List<Project> Projects, List<String> keys){
        mContext = context;
        mProjectAddaptor = new ProjectAddaptor(Projects,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mProjectAddaptor);
    }

    class ProjectItemView extends RecyclerView.ViewHolder{


        private TextView txtName,txtOrgName,txtPercentage;
        private CardView card_view;
        private String PID;
        private String key;
        private Project project;

        public ProjectItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.item_project,parent,false));

            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtOrgName = (TextView) itemView.findViewById(R.id.txtOrgName);
            txtPercentage = (TextView) itemView.findViewById(R.id.txtPercentage);
            card_view = (CardView) itemView.findViewById(R.id.card_view);

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, user_project.class);
                    intent.putExtra("PID",PID);
                    intent.putExtra("ProjectObj", (Serializable) project);
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Project project_t,String key){
            project = project_t;
            PID = project_t.getProject_ID();
            txtName.setText(project_t.getProject_Name());
            txtOrgName.setText(project_t.getProject_Org_Name());
            txtPercentage.setText(project_t.getPercentage());
            this.key = key;
        }



    }

    class ProjectAddaptor extends RecyclerView.Adapter<ProjectItemView>{
        private List<Project> mProject;
        private List<String> mKey;

        public ProjectAddaptor(List<Project> mProject, List<String> mKey) {
            this.mProject = mProject;
            this.mKey = mKey;
        }

        @NonNull
        @Override
        public ProjectItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProjectItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProjectItemView holder, int position) {
            holder.bind(mProject.get(position),mKey.get(position));
        }

        @Override
        public int getItemCount() {
            return mProject.size();
        }


    }

}
