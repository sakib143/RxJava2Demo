package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.Model.NotesModel;
import com.example.admn.rxjava2demo.R;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.completable.CompletableSubscribeOn;
import io.reactivex.schedulers.Schedulers;

public class CompletableObservableActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLoadCompletableDemo;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


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
                loadData();
                break;
        }
    }

    private void loadData() {
        updateNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(completableObserver());
    }

    private Completable updateNote() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    Thread.sleep(1000);
                    emitter.onComplete();
                }
            }
        });
    }

    private CompletableObserver completableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("==>", "onSubscribe");
                disposable = d;
            }

            @Override
            public void onComplete() {
                Log.e("==>", "onComplete: Note updated successfully!");
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
