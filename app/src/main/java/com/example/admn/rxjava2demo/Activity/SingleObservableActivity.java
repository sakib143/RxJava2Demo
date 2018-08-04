package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.R;

public class SingleObservableActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnLoadSingleObservable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_observable);

        getIds();
        setListner();
    }

    private void getIds() {
        try {
            btnLoadSingleObservable = findViewById(R.id.btnLoadSingleObservable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListner() {
        try {
            btnLoadSingleObservable.setOnClickListener(SingleObservableActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoadSingleObservable:

                break;
        }
    }
}
