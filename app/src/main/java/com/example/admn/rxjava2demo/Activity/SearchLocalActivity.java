package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.example.admn.rxjava2demo.Adapter.StudentAdapter;
import com.example.admn.rxjava2demo.Model.NotesModel;
import com.example.admn.rxjava2demo.Model.StudentModel;
import com.example.admn.rxjava2demo.R;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchLocalActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();
    private List<StudentModel> alStudent;
    private EditText edtSearch;
    private RecyclerView rlStudent;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_local);

        getIds();
        setData();
    }

    private void setData() {
        // TODO: 08-08-2018 Disposable for load arraylist
        disposable.add(loadObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(loadObserver()));

        // TODO: 08-08-2018 Disposable for searh value from array
        disposable.add(RxTextView.textChangeEvents(edtSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchStudentName()));

    }

    private DisposableObserver<TextViewTextChangeEvent> searchStudentName() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                List<StudentModel> alStudentTemp = new ArrayList<>();
                for (int i = 0; i <alStudent.size() ; i++) {
                    if(alStudent.get(i).getStrName().contains(textViewTextChangeEvent.text().toString())){
                        alStudentTemp.add(new StudentModel(textViewTextChangeEvent.text().toString()));
                    }
                }

                if(alStudentTemp.size() > 0){
                    studentAdapter = new StudentAdapter(SearchLocalActivity.this, alStudentTemp);
                    studentAdapter.notifyDataSetChanged();
                }
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
        try {
            edtSearch = findViewById(R.id.edtSearch);
            rlStudent = findViewById(R.id.rlStudent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Observable<StudentModel> loadObservable(){
        loadArrayList();
        return Observable.create(new ObservableOnSubscribe<StudentModel>() {
            @Override
            public void subscribe(ObservableEmitter<StudentModel> emitter) throws Exception {
                for (int i = 0; i < alStudent.size(); i++) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(alStudent.get(i));
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    private void loadArrayList() {
        alStudent = new ArrayList<>();
        alStudent.add(new StudentModel("Sakib"));
        alStudent.add(new StudentModel("Jeff"));
        alStudent.add(new StudentModel("Jack"));
        alStudent.add(new StudentModel("Tom"));
        alStudent.add(new StudentModel("Vickey"));
    }

    private DisposableObserver<StudentModel> loadObserver(){
        return new DisposableObserver<StudentModel>() {
            @Override
            public void onNext(StudentModel studentModel) {
                Log.e("==>","on next is calling "+studentModel.getStrName());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("==>","on Error is calling ");
            }

            @Override
            public void onComplete() {
                Log.e("==>","on complete is calling ");

                rlStudent.setLayoutManager(new LinearLayoutManager(SearchLocalActivity.this));
                studentAdapter = new StudentAdapter(SearchLocalActivity.this,alStudent);
                rlStudent.setAdapter(studentAdapter);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear(); // do not send event after activity has been destroyed
    }


}
