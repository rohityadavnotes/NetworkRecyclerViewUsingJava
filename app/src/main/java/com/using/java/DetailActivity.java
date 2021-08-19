package com.using.java;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.using.java.base.BaseActivity;
import com.using.java.data.remote.picasso.PicassoImageLoader;
import com.using.java.data.remote.picasso.PicassoImageLoadingListener;
import com.using.java.model.Android;

public class DetailActivity extends BaseActivity {

    public static final String TAG = DetailActivity.class.getSimpleName();

    private ProgressBar imageLoadingProgressBar;
    ImageView logoImageView;
    TextView codeNameTextView, versionNumbersTextView, apiLevelTextView, releaseDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initializeView() {
        imageLoadingProgressBar = findViewById(R.id.imageLoadingProgressBar);
        logoImageView           = findViewById(R.id.logoImageView);
        codeNameTextView        = findViewById(R.id.codeNameTextView);
        versionNumbersTextView  = findViewById(R.id.versionNumbersTextView);
        apiLevelTextView        = findViewById(R.id.apiLevelTextView);
        releaseDateTextView     = findViewById(R.id.releaseDateTextView);
    }

    @Override
    protected void initializeObject() {
        if (getIntent() != null && getIntent().hasExtra("parcelable_android_key")) {
            Android androidParcelable = (Android) getIntent().getParcelableExtra("parcelable_android_key");

            PicassoImageLoader.load(
                    this,
                    androidParcelable.getLogo(),
                    R.drawable.user_placeholder,
                    R.drawable.error_placeholder,
                    logoImageView,
                    new PicassoImageLoadingListener() {
                        @Override
                        public void imageLoadSuccess() {
                            imageLoadingProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void imageLoadError(Exception exception) {
                            Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                            imageLoadingProgressBar.setVisibility(View.GONE);
                        }
                    });

            codeNameTextView.setText(androidParcelable.getCodeName());
            versionNumbersTextView.setText(androidParcelable.getVersionNumbers());
            apiLevelTextView.setText(androidParcelable.getApiLevel());
            releaseDateTextView.setText(androidParcelable.getReleaseDate());
        }
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
    }
}