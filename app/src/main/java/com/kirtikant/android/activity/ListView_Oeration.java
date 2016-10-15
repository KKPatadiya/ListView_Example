package com.kirtikant.android.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.kirtikant.android.R;
import com.kirtikant.android.adapter.CustomAdapter;
import com.kirtikant.android.constant.getterandsetter;

import java.util.ArrayList;
import java.util.List;


public class ListView_Oeration extends AppCompatActivity {


    public static EditText et_fname, et_lname;
    public static boolean check_insert = true;
    public static int cur_position;
    public static Button btn_submit;
    private Toolbar mToobar;
    private ActionBar a_bar;
    private TextInputLayout tv_fname, tv_lname;
    private ListView l_view;
    private String firstname, lastname;
    private List<getterandsetter> my_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view__oeration);

        setupToolbar();
        setYourId();


    }


    private void setupToolbar() {
        mToobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToobar);
        a_bar = getSupportActionBar();
        a_bar.setTitle("ListView Example");
    }


    private void setYourId() {
        tv_fname = (TextInputLayout) findViewById(R.id.fname);
        tv_lname = (TextInputLayout) findViewById(R.id.lname);
        et_fname = (EditText) findViewById(R.id.et_fname);
        et_lname = (EditText) findViewById(R.id.et_lname);
        l_view = (ListView) findViewById(R.id.l_view);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        et_fname.addTextChangedListener(new MyTextWatcher(et_fname));
        et_lname.addTextChangedListener(new MyTextWatcher(et_lname));
        my_list = new ArrayList<>();
    }

    //button click event
    public void btn_submit(View view) {
        firstname = et_fname.getText().toString();
        lastname = et_lname.getText().toString();
        if (!checkFName())
            return;

        else if (!checkLName())
            return;

        else {
            if (check_insert) {
                getterandsetter get_set = new getterandsetter();
                get_set.setFirstName(firstname);
                get_set.setLastName(lastname);
                my_list.add(get_set);

                refresh_listview(my_list);
                et_fname.setText("");
                et_lname.setText("");
            } else {
                my_list.get(cur_position).setFirstName(firstname);
                my_list.get(cur_position).setLastName(lastname);
                et_fname.setText("");
                et_lname.setText("");
                check_insert = true;
                btn_submit.setText("Submit");
                refresh_listview(my_list);

            }

        }
    }

    private boolean checkLName() {
        if (et_lname.getText().toString().trim().isEmpty()) {
            tv_lname.setError("Please enter LastName");
            requestFocus(et_lname);
            return false;
        } else {
            tv_lname.setErrorEnabled(false);
        }
        return true;
    }


    private boolean checkFName() {
        if (et_fname.getText().toString().trim().isEmpty()) {
            tv_fname.setError("Please Enter FirstName");
            requestFocus(et_fname);
            return false;
        } else {
            tv_fname.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View et_lname) {
        if (et_lname.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void refresh_listview(List<getterandsetter> my_list) {
        CustomAdapter adapter = new CustomAdapter(ListView_Oeration.this, my_list);
        l_view.setAdapter(adapter);
    }


    private class MyTextWatcher implements TextWatcher {

        View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_fname:
                    checkFName();
                    break;
                case R.id.et_lname:
                    checkLName();
                    break;
            }
        }
    }


}
