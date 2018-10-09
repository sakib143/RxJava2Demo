package com.example.admn.rxjava2demo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admn.rxjava2demo.Model.LoginModel;
import com.example.admn.rxjava2demo.Model.User;
import com.example.admn.rxjava2demo.R;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ApiCallActivity extends AppCompatActivity {

    private Button btnPostApi,btnGetApiPassPath,btnGetApiListData;
    private CompositeDisposable disposable = new CompositeDisposable();
    private JSONObject jsonObject = new JSONObject();
    private LoginModel.Data modelData;
    private List<User> alUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_call);

        getIds();
        setListner();

        try {
            jsonObject.put("UserName", "v@gmail.com");
            jsonObject.put("Password", "123");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getIds() {
        btnPostApi = findViewById(R.id.btnPostApi);
        btnGetApiPassPath = findViewById(R.id.btnGetApiPassPath);
        btnGetApiListData = findViewById(R.id.btnGetApiListData);
    }

    private void setListner(){
        btnPostApi.setOnClickListener(onClickListener);
        btnGetApiPassPath.setOnClickListener(onClickListener);
        btnGetApiListData.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                switch (view.getId()){
                    case R.id.btnPostApi:
                        callLogin();
                        break;
                    case R.id.btnGetApiPassPath:
                        callGetApiPathDemo();
                        break;
                    case R.id.btnGetApiListData:
                        callGetApiDataInList();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void callGetApiDataInList() {
        disposable.add(Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllFootballFans")
                .build()
                .getObjectListObservable(User.class) // This returns you an Observable
                .subscribeOn(Schedulers.io()) // do the network call on another thread
                .observeOn(AndroidSchedulers.mainThread()) // return the result in mainThread
                .subscribeWith(new DisposableObserver<List<User>>() {

                    @Override
                    public void onNext(List<User> users) {
                        alUserList.addAll(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "Error ==>" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete ==>", " List size is : "+ alUserList.size());

                        for (int i = 0; i < alUserList.size(); i++) {
                            Log.e("==>"," " + alUserList.get(i).getFirstname());
                        }
                    }
                }));
    }

    private void callGetApiPathDemo() {
        disposable.add(Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAnUser/{userId}")
                .addPathParameter("userId", "1")
                .build()
                .getObjectObservable(User.class) // This returns you an Observable
                .subscribeOn(Schedulers.io()) // do the network call on another thread
                .observeOn(AndroidSchedulers.mainThread()) // return the result in mainThread
                .subscribeWith(new DisposableObserver<User>() {
                    @Override
                    public void onNext(User notesModel) {
                        Log.e("Success ==>", " User name: " + notesModel.getFirstname());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "Error ==>" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete ==>", " is calling ");
                    }
                }));
    }


    private void callLogin() {
        disposable.add(Rx2AndroidNetworking.post("UR_API/Login")
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(LoginModel.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<LoginModel>() {
                    @Override
                    public void onNext(LoginModel notesModel) {
                        modelData = notesModel.getData();
                        Log.e("Success ==>", " is calling " + modelData.getDisplayname());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "Error ==>" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete ==>", " is calling ");
                    }
                }));
    }
}
