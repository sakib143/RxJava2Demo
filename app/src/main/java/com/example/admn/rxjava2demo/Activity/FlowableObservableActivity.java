package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.R;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class FlowableObservableActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFlowableDemo;
    private Disposable disposable;

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
        switch (view.getId()) {
            case R.id.btnFlowableDemo:
                loadData();
                break;
        }
    }

    private void loadData() {
        getFlowableObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .reduce(0, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer number) {
                        //Log.e(TAG, "Result: " + result + ", new number: " + number);
                        return result + number;
                    }
                })
                .subscribe(getFlowableObserver());
    }

    private Flowable<Integer> getFlowableObservable() {
        return Flowable.range(1, 100);
    }


    private SingleObserver<Integer> getFlowableObserver() {
        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("==>", "onSubscribe");
                disposable = d;
            }

            @Override
            public void onSuccess(Integer integer) {
                Log.d("==>", "onSuccess: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("==>", "onError: " + e.getMessage());
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }


}
