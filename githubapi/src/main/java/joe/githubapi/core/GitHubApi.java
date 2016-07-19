package joe.githubapi.core;

import android.content.Context;
import android.content.pm.PackageManager;

import java.io.File;

import joe.githubapi.api.ActivityApi;
import joe.githubapi.api.AuthenticateApi;
import joe.githubapi.api.RepositoriesApi;
import joe.githubapi.api.SearchApi;
import joe.githubapi.api.UserApi;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
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
    private static boolean isDebug;
    private static File cachePath;
    private static final long SIZE_OF_CACHE = 5 * 1024 * 1024;

    private static OkHttpClient httpClient;
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static void init(Context context) {
        init(context, false);
    }

    public static void init(Context context, boolean is_debug) {
        isDebug = is_debug;
        try {
            CLIENT_ID = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("client_id");
            CLIENT_SECRET = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("client_secret");
            REDIRECT_URI = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("redirect_uri");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        cachePath = context.getCacheDir();
    }

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        if (isDebug) {
            okBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        }
        okBuilder.cache(new Cache(cachePath, SIZE_OF_CACHE));
        httpClient = okBuilder.build();
        Retrofit retrofit = builder.client(httpClient).build();
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