package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.R;

public class FlowableObservableActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnFlowableDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowable_observable);

        getIds();
        setListner();

    }

    private void setListner() {
        try {
            btnFlowableDemo.setOnClickListener(FlowableObservableActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getIds() {
        try {
            btnFlowableDemo = findViewById(R.id.btnFlowableDemo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFlowableDemo:

                break;
        }

    }
}
