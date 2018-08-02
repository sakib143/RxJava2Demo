package com.example.admn.rxjava2demo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.R;

public class MainActivity extends AppCompatActivity {

    private Button btnLoadArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getIds();
        setListner();


    }

    private void setListner() {
        btnLoadArrayList.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnLoadArrayList:
                    Intent intentSimpleDemo = new Intent(MainActivity.this,ArrayListDemoActivity.class);
                    startActivity(intentSimpleDemo);
                    break;
            }
        }
    };

    private void getIds() {
        btnLoadArrayList = findViewById(R.id.btnLoadArrayList);
    }
}
