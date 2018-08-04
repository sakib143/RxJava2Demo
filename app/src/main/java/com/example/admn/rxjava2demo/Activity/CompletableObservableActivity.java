package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.R;

public class CompletableObservableActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLoadCompletableDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completable_observable);

        getids();
        setListner();

    }

    private void setListner() {
        btnLoadCompletableDemo.setOnClickListener(CompletableObservableActivity.this);
    }

    private void getids() {
        try {
            btnLoadCompletableDemo = findViewById(R.id.btnLoadCompletableDemo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoadCompletableDemo:

                break;
        }
    }
}
