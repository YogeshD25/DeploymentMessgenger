package com.neml.deploymentaapproval.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neml.deploymentaapproval.Activities.DeploymentDetails;
import com.neml.deploymentaapproval.Model.ModelNotificationList;
import com.neml.deploymentaapproval.R;

import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DeploymentAdapter extends RecyclerView.Adapter<DeploymentAdapter.DeploymentViewHolder> {


private Context context;

private ArrayList<ModelNotificationList> deploymentList;

public DeploymentAdapter(Context mCtx, ArrayList<ModelNotificationList> productList) {
        this.context = mCtx;
        this.deploymentList = productList;
        }

@Override
public DeploymentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_deployment, null);
        return new DeploymentViewHolder(view);
        }


    @Override
    public void onBindViewHolder(DeploymentViewHolder holder, int position) {
        final ModelNotificationList deployment = deploymentList.get(position);
        holder.textDeploymentNo.setText(deployment.getDeploymentNo());
        holder.textRequesterName.setText(String.valueOf(deployment.getPreparedBy()));
        holder.textRequestermanager.setText(String.valueOf(deployment.getApprovedBy()));
        holder.textCreatedDate.setText(String.valueOf(deployment.getCreatedDate().substring(0,22)));
        holder.textUatApprovalName.setText(String.valueOf(deployment.getUatApprovar()));
        holder.textProjectName.setText(String.valueOf(deployment.getProjectName()));
        holder.textDeploymentType.setText(String.valueOf(deployment.getDeploymentType()));
        holder.textSRNno.setText(String.valueOf(deployment.getSrnNo()));
        holder.textVersionNo.setText(String.valueOf(deployment.getVersionNo()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(context,DeploymentDetails.class);
                Bundle b = new Bundle();
                b.putSerializable("serialzable",deployment);
                send.putExtras(b);
                context.startActivity(send);
            }
        });

        }


@Override
    public int getItemCount() {
        return deploymentList.size();
        }


    class DeploymentViewHolder extends RecyclerView.ViewHolder {

    TextView textDeploymentNo, textRFCseqno, textDeploymentType, textVersionNo, textSRNno,
            textProjectName, textUatApprovalName, textCreatedDate, textRequesterName, textRequestermanager,textApplicationURL;
    ImageView imageView;
    CardView cardView;

    public DeploymentViewHolder(View itemView) {
        super(itemView);

        textDeploymentNo = itemView.findViewById(R.id.textDeploymentNo);
        textRequesterName = itemView.findViewById(R.id.textRequesterName);
        textRequestermanager = itemView.findViewById(R.id.textRequestermanager);
        textCreatedDate = itemView.findViewById(R.id.textCreatedDate);
        textUatApprovalName = itemView.findViewById(R.id.textUatApprovalName);
        textProjectName = itemView.findViewById(R.id.textProjectName);
        textDeploymentType = itemView.findViewById(R.id.textDeploymentType);
        textSRNno = itemView.findViewById(R.id.textSRNno);
        textVersionNo = itemView.findViewById(R.id.textVersionNo);
        cardView = itemView.findViewById(R.id.deploymentCard);

        imageView = itemView.findViewById(R.id.imageView);



    }
}
}
