package app.sarc.com.sarc.Common.NetworkingMethod;

import java.util.concurrent.TimeUnit;

import app.sarc.com.sarc.Common.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aron on 26/3/2018.
 */

public class HttpMethods {

    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private ServiceAPI serviceAPI;

    public HttpMethods(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        serviceAPI = retrofit.create(ServiceAPI.class);

    }

    private static class SingletonInstance{
        public static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return SingletonInstance.INSTANCE;
    }

    public ServiceAPI getServiceAPI() {
        return serviceAPI;
    }
}
