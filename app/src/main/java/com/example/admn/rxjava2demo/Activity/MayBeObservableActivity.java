package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.Model.NotesModel;
import com.example.admn.rxjava2demo.R;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.maybe.MaybeSubscribeOn;
import io.reactivex.schedulers.Schedulers;

public class MayBeObservableActivity extends AppCompatActivity {

    private Button btnMayBeLoadData;
    private Disposable disposable;  // We can not use CompositeDisposable in MayBe Observable.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_may_be_observable);

        getIds();

        btnMayBeLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMayBeObservable()
                        .subscribeOn(Schedulers.io())
                        .subscribe(getNoteObserver());
            }
        });
    }

    private void getIds() {
        try {
            btnMayBeLoadData = findViewById(R.id.btnMayBeLoadData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Maybe<NotesModel> getMayBeObservable() {
        return Maybe.create(new MaybeOnSubscribe<NotesModel>() {
            @Override
            public void subscribe(MaybeEmitter<NotesModel> emitter) throws Exception {
                NotesModel note = new NotesModel(1, "Call brother!");
                if (!emitter.isDisposed()) {
                    emitter.onSuccess(note);
                }
            }
        });
    }

    private MaybeObserver<NotesModel> getNoteObserver() {
        return new MaybeObserver<NotesModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                Log.e("==>>","On onSubscribe in May be observable");
            }

            @Override
            public void onSuccess(NotesModel note) {
                Log.e("==>", "onSuccess: in Maybe Observable" + note.getNote());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("==>", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("==>", "onComplete");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
