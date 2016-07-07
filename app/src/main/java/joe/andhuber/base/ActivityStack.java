package joe.andhuber.base;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class ActivityStack {

    private static ArrayList<AppCompatActivity> activities = new ArrayList<>();

    public static void add(AppCompatActivity appCompatActivity) {
        activities.add(appCompatActivity);
    }

    public static void remove(AppCompatActivity appCompatActivity) {
        activities.remove(appCompatActivity);
    }

    public static void clear() {
        for (AppCompatActivity activity : activities) {
            activity.finish();
        }
        activities.clear();
    }
}
