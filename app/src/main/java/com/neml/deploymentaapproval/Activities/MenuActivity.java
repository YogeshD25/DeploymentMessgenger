package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Deployment Approval");
        setSupportActionBar(toolbar);
        initUI();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_items_page, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuAbout:
                Toast.makeText(this, "You clicked about", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "You clicked logout", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.menuNotification:
                Intent intent = new Intent(MenuActivity.this,NotificationList.class);
                startActivity(intent);

        }
        return true;
    }

    private void initUI() {
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deploymentList = new ArrayList<>();
        deploymentList.add(
                new ModelDisplayDetails("Dy145","23","Shraddha","Shipra","neml.in","23/10/2017","Shradhha",
                        "E permit",
                        "UAT",
                        "23",
                        "1.0.36"));
        deploymentList.add(
                new ModelDisplayDetails("Dy146","23","Shraddha","Shipra","neml.in","23/10/2017","Shradhha",
                        "E Samriddhi",
                        "UAT",
                        "23",
                        "1.0.36"));
        deploymentList.add(
                new ModelDisplayDetails("Dy147","23","Shraddha","Shipra","neml.in","23/10/2017","Shradhha",
                        "PETX",
                        "UAT",
                        "23",
                        "1.0.36"));
        deploymentList.add(
                new ModelDisplayDetails("Dy148","23","Shraddha","Shipra","neml.in","23/10/2017","Shradhha",
                        "UPP",
                        "UAT",
                        "23",
                        "1.0.36"));




        DeploymentAdapter adapter = new DeploymentAdapter(this, deploymentList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
