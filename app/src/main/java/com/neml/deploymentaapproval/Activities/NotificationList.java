package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.os.Bundle;

import com.neml.deploymentaapproval.Adapters.DeploymentAdapter;
import com.neml.deploymentaapproval.Adapters.NotifyListAdapter;
import com.neml.deploymentaapproval.Model.ModelDisplayDetails;
import com.neml.deploymentaapproval.Model.ModelNotificationListItem;
import com.neml.deploymentaapproval.R;

import java.util.ArrayList;

public class NotificationList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ModelNotificationListItem> notifyItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        initUI();
    }

    private void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notifyItemList = new ArrayList<>();
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));

        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        notifyItemList.add(
                new ModelNotificationListItem("E Permit","UAT","1.0.37","Bug Fixes"));
        NotifyListAdapter adapter = new NotifyListAdapter(NotificationList.this, notifyItemList);
        recyclerView.setAdapter(adapter);
    }
}
