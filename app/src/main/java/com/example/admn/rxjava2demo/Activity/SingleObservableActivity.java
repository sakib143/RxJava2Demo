package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.Model.NotesModel;
import com.example.admn.rxjava2demo.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SingleObservableActivity extends AppCompatActivity implements View.OnClickListener{


    private Button btnLoadSingleObservable;
    private Disposable disposable;  //Here we can not use CompositeDisposable in Single and SingleObserver.


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
                loadData();
                break;
        }
    }

    private void loadData() {

        getNoteObservable()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(getNotesObserver());

    }

    private Single<NotesModel> getNoteObservable() {
        return Single.create(new SingleOnSubscribe<NotesModel>() {
            @Override
            public void subscribe(SingleEmitter<NotesModel> emitter) throws Exception {
                NotesModel note = new NotesModel(1, "Buy milk!");
                emitter.onSuccess(note);
            }
        });
    }

    private SingleObserver<NotesModel> getNotesObserver() {
        return new SingleObserver<NotesModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("==>", "onSubscribe in Single Observer");
                disposable = d;
            }

            @Override
            public void onSuccess(NotesModel note) {
                Log.e("==>", "onSuccess: in Single Observable" + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("==> ", "onError: in Single Observable" + e.getMessage());
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}
