package com.using.java.base;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    /*
     ***********************************************************************************************
     ********************************* BaseActivity abstract methods *******************************
     ***********************************************************************************************
     */
    @LayoutRes
    protected abstract int getLayoutID();
    protected abstract void initializeView();
    protected abstract void initializeObject();
    protected abstract void initializeToolBar();
    protected abstract void initializeCallbackListener();
    protected abstract void addTextChangedListener();
    protected abstract void setOnClickListener();
    /*
     ***********************************************************************************************
     ********************************* Activity lifecycle methods **********************************
     ***********************************************************************************************
     */
    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate(Bundle savedInstanceState)");

        setContentView(getLayoutID());

        initializeView();
        initializeObject();
        initializeToolBar();
        initializeCallbackListener();
        addTextChangedListener();
        setOnClickListener();
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    @CallSuper
    protected void onRestart() { /* Only called after onStop() */
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed()");
    }
}
