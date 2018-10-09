package com.example.admn.rxjava2demo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admn.rxjava2demo.R;

public class MainActivity extends AppCompatActivity {

    private Button btnLoadArrayList,btnApiCallingDemos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIds();
        setListner();
    }

    private void setListner() {
        try {
            btnLoadArrayList.setOnClickListener(onClickListener);
            btnApiCallingDemos.setOnClickListener(onClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                switch (view.getId()){
                    case R.id.btnLoadArrayList:
                        Intent intentSimpleDemo = new Intent(MainActivity.this,SampleDemoActivity.class);
                        startActivity(intentSimpleDemo);
                        break;
                    case R.id.btnApiCallingDemos:
                        Intent intent = new Intent(MainActivity.this,ApiCallActivity.class);
                        startActivity(intent);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void getIds() {
        try {
            btnLoadArrayList = findViewById(R.id.btnLoadArrayList);
            btnApiCallingDemos = findViewById(R.id.btnApiCallingDemos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
