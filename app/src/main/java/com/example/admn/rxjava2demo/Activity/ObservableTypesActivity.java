package com.example.admn.rxjava2demo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.R;

public class ObservableTypesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSingleObservable,btnMayBeObservable,btnCompletableObservable,btnFlowableObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_types);

        getIds();
        setListner();

    }

    private void getIds() {
        try {
            btnSingleObservable = findViewById(R.id.btnSingleObservable);
            btnMayBeObservable = findViewById(R.id.btnMayBeObservable);
            btnCompletableObservable = findViewById(R.id.btnCompletableObservable);
            btnFlowableObservable = findViewById(R.id.btnFlowableObservable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListner() {
        try {
            btnSingleObservable.setOnClickListener(ObservableTypesActivity.this);
            btnMayBeObservable.setOnClickListener(ObservableTypesActivity.this);
            btnCompletableObservable.setOnClickListener(ObservableTypesActivity.this);
            btnFlowableObservable.setOnClickListener(ObservableTypesActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.btnSingleObservable:
                    Intent intentSingleObservable = new Intent(ObservableTypesActivity.this, SingleObservableActivity.class);
                    startActivity(intentSingleObservable);
                    break;
                case R.id.btnMayBeObservable:
                    Intent intentMayBeObservable = new Intent(ObservableTypesActivity.this, MayBeObservableActivity.class);
                    startActivity(intentMayBeObservable);
                    break;
                case R.id.btnCompletableObservable:
                    Intent intenCompletable = new Intent(ObservableTypesActivity.this,CompletableObservableActivity.class);
                    startActivity(intenCompletable);
                    break;
                case R.id.btnFlowableObservable:
                    Intent intentFlowableDemo = new Intent(ObservableTypesActivity.this,FlowableObservableActivity.class);
                    startActivity(intentFlowableDemo);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}