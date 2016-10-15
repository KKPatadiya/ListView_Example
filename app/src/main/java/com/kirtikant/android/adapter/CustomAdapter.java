package com.kirtikant.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.kirtikant.android.R;
import com.kirtikant.android.activity.ListView_Oeration;
import com.kirtikant.android.constant.getterandsetter;

import java.util.List;

/**
 * Created by kirtikant on 9/10/16.
 */
public class CustomAdapter extends BaseAdapter {

    Context m_context;
    List<getterandsetter> m_list;
    private LayoutInflater inflator;

    public CustomAdapter(Context m_context, List<getterandsetter> m_list) {

        this.m_context = m_context;
        this.m_list = m_list;

    }

    @Override
    public int getCount() {
        return m_list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        inflator = LayoutInflater.from(m_context);

        ViewHolder m_viewHolder = null;
        if (view == null) {
            view = inflator.inflate(R.layout.list_item, null);
            m_viewHolder = new ViewHolder();

            m_viewHolder.tv_firstname = (TextView) view.findViewById(R.id.tv_fname);
            m_viewHolder.tv_lastname = (TextView) view.findViewById(R.id.tv_lname);
            m_viewHolder.btn_edit = (ImageButton) view.findViewById(R.id.btn_edit);
            m_viewHolder.btn_delete = (ImageButton) view.findViewById(R.id.btn_delete);

            view.setTag(m_viewHolder);

        } else {
            m_viewHolder = (ViewHolder) view.getTag();
        }

        m_viewHolder.tv_firstname.setText(m_list.get(i).getFirstName());
        m_viewHolder.tv_lastname.setText(m_list.get(i).getLastName());

        m_viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListView_Oeration.et_fname.setText(m_list.get(i).getFirstName());
                ListView_Oeration.et_lname.setText(m_list.get(i).getLastName());
                ListView_Oeration.check_insert = false;
                ListView_Oeration.cur_position = i;
                ListView_Oeration.btn_submit.setText("Update");

            }
        });

        m_viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_list.remove(i);
                notifyDataSetChanged();
                ListView_Oeration.btn_submit.setText("Submit");
                ListView_Oeration.check_insert = true;


            }
        });
        return view;
    }

    public class ViewHolder {
        TextView tv_firstname;
        TextView tv_lastname;
        ImageButton btn_edit;
        ImageButton btn_delete;
    }
}
