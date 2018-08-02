package com.example.admn.rxjava2demo.Activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.Model.NotesModel;
import com.example.admn.rxjava2demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ArrayListDemoActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private Button btnLoadArray,btnDoSingleOperation;
    private List<NotesModel> alNotes;
    private String strTemp = "";

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListner() {
        try {
            btnLoadArray.setOnClickListener(mClickListner);
            btnDoSingleOperation.setOnClickListener(mClickListner);
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
                        loadArrayObservable();
                        break;
                    case R.id.btnDoSingleOperation:
                        doSingleOperation();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

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

    // TODO: 02-08-2018 Perform single Operation START
    private void doSingleOperation() {
        disposable.add(loadStringValueObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(setValueObserver()));
    }

    private Observable<String> loadStringValueObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                strTemp = "Sakib";
                return Observable.just(strTemp);
            }
        });
    }

    private DisposableObserver<String> setValueObserver (){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.e("==> ","Value of string is "+s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("==> ","onComplete() is calling AND  value of strTemp is ?? "+strTemp);
            }
        };
    }
    // TODO: 02-08-2018 Perform single Operation END

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear(); // do not send event after activity has been destroyed
    }
}
