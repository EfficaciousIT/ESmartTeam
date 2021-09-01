package com.mobi.efficacious.esmartteam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobi.efficacious.esmartteam.R;
import com.mobi.efficacious.esmartteam.entity.CustomerList;

import java.util.ArrayList;

public class Customer_Spinner_Adapter extends BaseAdapter {

    private final Context context;
    ArrayList<CustomerList> menus = new ArrayList<CustomerList>();
    String page_selected;
    ImageHolder holder = null;
    public Customer_Spinner_Adapter(Context context, ArrayList<CustomerList> Menus, String page) {
        super();
        this.context = context;
        this.menus = Menus;
        this.page_selected=page;
    }
    static class ImageHolder
    {
        TextView textview1;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        try {
            if(row == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.spinner_item, parent, false);
                holder = new ImageHolder();
                row.setTag(holder);
            }
            else
            {
                holder = (ImageHolder)row.getTag();
            }
            holder.textview1=row.findViewById(R.id.textview1);
            holder.textview1.setText(menus.get(position).getCustName());
        }catch (Exception ex)
        {

        }

        return row;
    }



}