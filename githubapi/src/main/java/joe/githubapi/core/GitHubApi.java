package joe.githubapi.core;

import android.content.Context;
import android.content.pm.PackageManager;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import joe.githubapi.api.ActivityApi;
import joe.githubapi.api.AuthenticateApi;
import joe.githubapi.api.RepositoriesApi;
import joe.githubapi.api.SearchApi;
import joe.githubapi.api.UserApi;
import joe.githubapi.util.NetUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description
 * Created by chenqiao on 2016/7/1.
 */
public class GitHubApi {

    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String REDIRECT_URI;
    public static final String API_BASE_URL = "https://api.github.com";
    private static final long SIZE_OF_CACHE = 5 * 1024 * 1024;
    private static Retrofit retrofit;
    private static WeakReference<Context> mContext;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static void init(Context context) {
        init(context, false);
    }

    public static void init(final Context context, boolean is_debug) {
        mContext = new WeakReference<>(context);
        try {
            CLIENT_ID = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("client_id");
            CLIENT_SECRET = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("client_secret");
            REDIRECT_URI = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("redirect_uri");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        File cachePath = context.getCacheDir();

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        if (is_debug) {
            okBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        }
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(15, TimeUnit.SECONDS).maxStale(1, TimeUnit.DAYS);
        final CacheControl control = cacheBuilder.build();
        okBuilder.cache(new Cache(cachePath, SIZE_OF_CACHE));
        okBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtil.isConnected(mContext.get())) {
                    request = request.newBuilder().cacheControl(control).build();
                }
                Response originalResponse = chain.proceed(request);
                if (NetUtil.isConnected(mContext.get())) {
                    int maxAge = 60; // read from cache
                    return originalResponse.newBuilder().removeHeader("Pragma").header("Cache-Control", "public ,max-age=" + maxAge).build();
                } else {
                    int maxStale = 60 * 60 * 24;
                    return originalResponse.newBuilder().removeHeader("Pragma").header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale).build();
                }
            }
        });

        OkHttpClient httpClient = okBuilder.build();
        retrofit = builder.client(httpClient).build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static AuthenticateApi getAuthenticateApi() {
        return new AuthenticateApi();
    }

    public static UserApi getUserApi() {
        return new UserApi();
    }

    public static RepositoriesApi getRepositoriesApi() {
        return new RepositoriesApi();
    }

    public static ActivityApi getActivityApi() {
        return new ActivityApi();
    }

    public static SearchApi getSearchApi() {
        return new SearchApi();
    }
}