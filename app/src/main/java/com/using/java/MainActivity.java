package com.using.java;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.using.java.adapter.RecyclerViewAdapter;
import com.using.java.adapter.RecyclerViewItemClickListener;
import com.using.java.adapter.RecyclerViewTouchListener;
import com.using.java.base.BaseActivity;
import com.using.java.data.remote.ApiService;
import com.using.java.data.remote.ApiServiceGenerator;
import com.using.java.model.Android;
import com.using.java.model.Data;
import java.util.ArrayList;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Android> androidArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initializeView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initializeObject() {
        androidArrayList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);

        getVersions();
    }

    @Override
    protected void initializeToolBar() {
    }

    @Override
    protected void initializeCallbackListener() {
    }

    @Override
    protected void addTextChangedListener() {
    }

    @Override
    protected void setOnClickListener() {
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "position : " + androidArrayList.get(position).getCodeName(), Toast.LENGTH_SHORT).show();

                Android android = androidArrayList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("parcelable_android_key", android);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "position : " + androidArrayList.get(position).getCodeName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void getVersions() {
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        ApiService apiService = ApiServiceGenerator.createService(MainActivity.this, ApiService.class);

        Observable<Response<Data>> observable = apiService.getAndroidVersion();
        Observer<Response<Data>> observer = new Observer<Response<Data>>() {

            @Override
            public void onSubscribe(Disposable disposable) {
                progressDialog.show();
            }

            @Override
            public void onNext(Response<Data> response) {
                progressDialog.dismiss();
                if (response != null) {
                    if (response.body() != null && response.isSuccessful()) {
                        androidArrayList = response.body().getData();
                        Toast.makeText(getApplicationContext(),""+androidArrayList.size(), Toast.LENGTH_SHORT).show();
                        recyclerViewAdapter.setAdapterListData(androidArrayList);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
            }
        };

        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}