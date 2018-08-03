package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admn.rxjava2demo.R;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.DisposableLambdaObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GetEdittextStringActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private String strTemp = "";
    private Button btnGetValue;
    private EditText edtEnterValue;
    private TextView tvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_edittext_string);

        getIds();
        setListner();
    }

    private void setListner() {
        try {
            btnGetValue.setOnClickListener(onClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                switch (view.getId()){
                    case R.id.btnGetValue:
                        getEdittextValue();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void getEdittextValue() {
        disposable.add(getEditTextObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(setEditTextObserve()));
    }

    private Observable<String> getEditTextObservable (){
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just(edtEnterValue.getText().toString().trim());
            }
        });
    }

    private DisposableObserver<String> setEditTextObserve(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                tvValue.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void getIds() {
        try {
            btnGetValue = findViewById(R.id.btnGetValue);
            edtEnterValue = findViewById(R.id.edtEnterValue);
            tvValue = findViewById(R.id.tvValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear(); // do not send event after activity has been destroyed
    }

}
