package com.example.admn.rxjava2demo.Activity;

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

public class ArrayListActivity extends AppCompatActivity implements View.OnClickListener{

    private List<NotesModel> alNotes;
    private Button btnLoadArrayList;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);

        getIds();
        setListner();
    }

    private void setListner() {
        try {
            btnLoadArrayList.setOnClickListener(ArrayListActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getIds() {
        try {
            btnLoadArrayList = findViewById(R.id.btnLoadArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoadArrayList:
                loadArrayObservable();
                break;
        }
    }

    // TODO: 02-08-2018 Load arraylist static START
    private void loadArrayObservable() {
        disposable.add(getNotesDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getNotesObserver()));
    }

    private Observable<NotesModel> getNotesDetails() {
        alNotes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            alNotes.add(new NotesModel(i, "Value at position " + i));
        }
        return Observable.create(new ObservableOnSubscribe<NotesModel>() {
            @Override
            public void subscribe(ObservableEmitter<NotesModel> emitter) throws Exception {
                for (NotesModel note : alNotes) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(note);
                    }
                }
                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    private DisposableObserver<NotesModel> getNotesObserver() {
        return new DisposableObserver<NotesModel>() {
            @Override
            public void onNext(NotesModel notesModel) {
                Log.e("List size is ==>", " " + notesModel.getNote());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("onComplete ==>", " is calling ");
            }
        };
    }
    // TODO: 02-08-2018 Load arraylist static END

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear(); // do not send event after activity has been destroyed
    }
}
