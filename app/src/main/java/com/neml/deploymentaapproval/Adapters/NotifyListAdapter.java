package com.neml.deploymentaapproval.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neml.deploymentaapproval.Activities.DeploymentDetails;
import com.neml.deploymentaapproval.Model.ModelNotificationListItem;
import com.neml.deploymentaapproval.R;

import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NotifyListAdapter extends RecyclerView.Adapter<NotifyListAdapter.NotifyListViewHolder> {


    private Context context;

    private ArrayList<ModelNotificationListItem> notifyList;

    public NotifyListAdapter(Context mCtx, ArrayList<ModelNotificationListItem> notifyList) {
        this.context = mCtx;
        this.notifyList = notifyList;
    }

    @Override
    public NotifyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_notification, null);
        return new NotifyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotifyListViewHolder holder, int position) {
        ModelNotificationListItem notify = notifyList.get(position);
        holder.textApplication.setText(notify.getApplication());
        holder.textReleaseType.setText(notify.getReleaseType());
        holder.textVersion.setText(String.valueOf(notify.getVersion()));
        holder.textReleaseinfo.setText(String.valueOf(notify.getReleaseInfo()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context, DeploymentDetails.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return notifyList.size();
    }


    class NotifyListViewHolder extends RecyclerView.ViewHolder {

        TextView textApplication, textReleaseType, textVersion, textReleaseinfo;
        CardView cardView;

        public NotifyListViewHolder(View itemView) {
            super(itemView);

            textApplication = itemView.findViewById(R.id.textApplication);
            textReleaseType = itemView.findViewById(R.id.textReleaseType);
            textVersion = itemView.findViewById(R.id.textVersion);
            textReleaseinfo = itemView.findViewById(R.id.textReleaseinfo);
            cardView = itemView.findViewById(R.id.notifyCard);




        }
    }
}
