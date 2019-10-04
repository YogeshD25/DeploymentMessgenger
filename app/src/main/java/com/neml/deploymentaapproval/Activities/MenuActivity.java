package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.neml.deploymentaapproval.Adapters.DeploymentAdapter;
import com.neml.deploymentaapproval.Model.ModelDisplayDetails;
import com.neml.deploymentaapproval.NetworkUtils.HttpsTrustManager;
import com.neml.deploymentaapproval.NetworkUtils.NetworkUtils;
import com.neml.deploymentaapproval.FCMConnection.*;
import com.neml.deploymentaapproval.Logger.*;
import com.neml.deploymentaapproval.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ModelDisplayDetails> deploymentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initUI();
    }

    private void initUI() {
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deploymentList = new ArrayList<>();
        deploymentList.add(
                new ModelDisplayDetails("ABCD","ABCD","ABCD","ABCD","ABCD","ABCD","ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD"));
        deploymentList.add(
                new ModelDisplayDetails("ABCD","ABCD","ABCD","ABCD","ABCD","ABCD","ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD"));
        deploymentList.add(
                new ModelDisplayDetails("ABCD","ABCD","ABCD","ABCD","ABCD","ABCD","ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD"));
        deploymentList.add(
                new ModelDisplayDetails("ABCD","ABCD","ABCD","ABCD","ABCD","ABCD","ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD",
                        "ABCD"));




        DeploymentAdapter adapter = new DeploymentAdapter(this, deploymentList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
