package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.R;

public class MayBeObservableActivity extends AppCompatActivity {

    private Button btnMayBeLoadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_be_observable);

        getIds();


        btnMayBeLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void getIds() {
        try {
            btnMayBeLoadData = findViewById(R.id.btnMayBeLoadData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
