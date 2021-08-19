package com.using.java.data.remote;

import com.using.java.model.Data;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    @GET(RemoteConfiguration.VERSION)
    Observable<Response<Data>> getAndroidVersion();
}
