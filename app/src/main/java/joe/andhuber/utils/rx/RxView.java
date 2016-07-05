package joe.andhuber.utils.rx;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Observable;
import rx.Subscription;

/**
 * Description
 * Created by chenqiao on 2016/6/30.
 */
public class RxView {
    private static HashMap<String, ArrayList<Subscription>> subscriptions = new HashMap<>();

    public static Observable<Void> click(@NonNull View view) {
        return Observable.create(new ViewClickOnSubscribe(view));
    }

    public static void add(String tag, Subscription subscription) {
        ArrayList<Subscription> array;
        if ((array = subscriptions.get(tag)) == null) {
            array = new ArrayList<>();
        }
        array.add(subscription);
        subscriptions.put(tag, array);
    }

    public static void release(String tag) {
        ArrayList<Subscription> array = subscriptions.get(tag);
        if (array != null && array.size() > 0) {
            for (Subscription subscription : array) {
                subscription.unsubscribe();
            }
            subscriptions.remove(tag);
        }
    }
}
