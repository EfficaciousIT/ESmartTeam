package com.mobi.efficacious.esmartteam.adapter;


import android.content.Context;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.mobi.efficacious.esmartteam.Fragment.DispatchedDetails_fragement;
import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.activity.MainActivity;
import com.mobi.efficacious.esmartteam.entity.TaskList;

import java.util.ArrayList;


public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {

    private ArrayList<TaskList> dataList;
    Context mcontext;
    String RequisitionId;
    public TaskListAdapter(ArrayList<TaskList> dataList, Context context) {
        this.dataList = dataList;
        this.mcontext = context;
    }

    @Override
    public TaskListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tasklistadapter_row, parent, false);
        return new TaskListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskListViewHolder holder, final int position) {
        try {
            holder.txtId.setText(String.valueOf(dataList.get(position).getIntRequisitionId()));
            holder.txtName.setText(dataList.get(position).getVchName());
            holder.txtRequisition.setText(dataList.get(position).getVchRequisition());
            holder.txtMobileNo.setText(dataList.get(position).getVchMobile());
            holder.txtAddress.setText(dataList.get(position).getVchAddress());
            holder.txtTargetDate.setText(dataList.get(position).getDtTargetDate());
            holder.txtDescription.setText(dataList.get(position).getVchRemark());
            holder.txtStatus.setText(dataList.get(position).getVchStatus());
        }catch (Exception ex)
        {

        }


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    RequisitionId = String.valueOf(dataList.get(position).getIntRequisitionId());
                    DispatchedDetails_fragement dispatchedDetails_fragement = new DispatchedDetails_fragement();
                    Bundle b = new Bundle();
                    b.putString("Tag_RequisitionId", String.valueOf(dataList.get(position).getIntRequisitionId()));
                    b.putString("Tag_Name", dataList.get(position).getVchName());
                    b.putString("Tag_Requisition", dataList.get(position).getVchRequisition());
                    b.putString("Tag_MobileNo", dataList.get(position).getVchMobile());
                    b.putString("Tag_Address", dataList.get(position).getVchAddress());
                    b.putString("Tag_TargetDate", dataList.get(position).getDtTargetDate());
                    b.putString("Tag_Description", dataList.get(position).getVchRemark());
                    b.putString("Tag_Status", dataList.get(position).getVchStatus());
                    dispatchedDetails_fragement.setArguments(b);
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main,dispatchedDetails_fragement).addToBackStack(null).commitAllowingStateLoss();

                }catch (Exception ex)
                {

                }

//                Intent in = new Intent(getContext(), DispatchedDetails_fragement.class);
//                in.putExtras(b);
//                getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class TaskListViewHolder extends RecyclerView.ViewHolder {

        TextView txtId;
        TextView txtName;
        TextView txtRequisition;
        TextView txtMobileNo;
        Button btncall;
        TextView txtAddress;
        TextView txtTargetDate;
        TextView txtDescription;
        TextView txtStatus;
        TextView edit;

        TaskListViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView)itemView.findViewById(R.id.IdtextView_tasklist);
            txtName = (TextView)itemView.findViewById(R.id.NametextView_tasklist);
            txtRequisition=(TextView)itemView.findViewById(R.id.RequisitiontextView_tasklist);
            txtMobileNo=(TextView)itemView.findViewById(R.id.MobiletextView_tasklist);
            txtAddress=(TextView)itemView.findViewById(R.id.addresstextView_tasklist);
            txtTargetDate = (TextView)itemView.findViewById(R.id.TargetDatetextView_tasklist);
            txtDescription = (TextView)itemView.findViewById(R.id.DescriptiontextView_tasklist);
            txtStatus = (TextView)itemView.findViewById(R.id.statustextView_tasklist);
            edit = (TextView)itemView.findViewById(R.id.Edit);
        }
    }

}

