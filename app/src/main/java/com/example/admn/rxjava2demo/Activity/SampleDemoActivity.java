package com.example.admn.rxjava2demo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.Model.NotesModel;
import com.example.admn.rxjava2demo.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SampleDemoActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private Button btnLoadArray,btnDoSingleOperation,btnTextChange,btnObservableTypes,btnSearchFromArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list_demo);

        getIds();
        setListner();

    }

    private void getIds() {
        try {
            btnLoadArray = findViewById(R.id.btnLoadArray);
            btnDoSingleOperation = findViewById(R.id.btnDoSingleOperation);
            btnTextChange = findViewById(R.id.btnTextChange);
            btnObservableTypes = findViewById(R.id.btnObservableTypes);
            btnSearchFromArray = findViewById(R.id.btnSearchFromArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListner() {
        try {
            btnLoadArray.setOnClickListener(mClickListner);
            btnDoSingleOperation.setOnClickListener(mClickListner);
            btnTextChange.setOnClickListener(mClickListner);
            btnObservableTypes.setOnClickListener(mClickListner);
            btnSearchFromArray.setOnClickListener(mClickListner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener mClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                switch (view.getId()) {
                    case R.id.btnLoadArray:
                        Intent intentArrayList = new Intent(SampleDemoActivity.this,ArrayListActivity.class);
                        startActivity(intentArrayList);
                        break;
                    case R.id.btnDoSingleOperation:
                        Intent intent = new Intent(SampleDemoActivity.this, GetEdittextStringActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnTextChange:
                        Intent intentTextChange = new Intent(SampleDemoActivity.this,EditTextChangeActivity.class);
                        startActivity(intentTextChange);
                        break;
                    case R.id.btnObservableTypes:
                        Intent intentObservableList = new Intent(SampleDemoActivity.this,ObservableTypesActivity.class);
                        startActivity(intentObservableList);
                        break;
                    case R.id.btnSearchFromArray:
                        Intent intentSearchFromArray = new Intent(SampleDemoActivity.this,SearchLocalActivity.class);
                        startActivity(intentSearchFromArray);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear(); // do not send event after activity has been destroyed
    }
}
