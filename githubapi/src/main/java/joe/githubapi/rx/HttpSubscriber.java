package joe.githubapi.rx;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import joe.githubapi.model.ErrorInfo;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Description
 * Created by chenqiao on 2016/7/5.
 */
public abstract class HttpSubscriber<T> extends Subscriber<T> {
    @Override
    public void onError(Throwable e) {
        HttpException httpException = (HttpException) e;
        ErrorInfo errorInfo;
        try {
            String errorBody = httpException.response().errorBody().string();
            Gson gson = new Gson();
            errorInfo = gson.fromJson(errorBody, ErrorInfo.class);
        } catch (IOException e1) {
            e1.printStackTrace();
            errorInfo = new ErrorInfo();
            errorInfo.setMessage(e.toString());
        } catch (JsonSyntaxException e2) {
            errorInfo = new ErrorInfo();
            errorInfo.setMessage(e.toString());
        }
        onHttpError(errorInfo);
    }

    public abstract void onHttpError(ErrorInfo info);
}
