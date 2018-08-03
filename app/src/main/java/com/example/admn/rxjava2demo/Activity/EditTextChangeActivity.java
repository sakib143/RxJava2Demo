package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admn.rxjava2demo.R;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EditTextChangeActivity extends AppCompatActivity {

    private EditText edtValue;
    private TextView tvEnteredValue;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_change);

        getIds();

        disposable.add(RxTextView.textChangeEvents(edtValue)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchContactsTextWatcher()));

    }

    private DisposableObserver<TextViewTextChangeEvent> searchContactsTextWatcher() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                tvEnteredValue.setText("You entered ==> "+textViewTextChangeEvent.text());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("==>", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }


    private void getIds() {
        edtValue = findViewById(R.id.edtValue);
        tvEnteredValue = findViewById(R.id.tvEnteredValue);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear(); // do not send event after activity has been destroyed
    }
}
