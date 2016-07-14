package joe.andhuber.utils;

import android.text.TextUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Description
 * Created by chenqiao on 2016/7/6.
 */
public class MapUtil {

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FieldName {
        String value() default "";
    }

    public static HashMap<String, String> toMap(Object object) {
        HashMap<String, String> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class || field.getType() == int.class || field.getType() == long.class) {
                String key;
                String value = null;
                field.setAccessible(true);
                if (field.isAnnotationPresent(FieldName.class)) {
                    FieldName fieldName = field.getAnnotation(FieldName.class);
                    key = fieldName.value();
                } else {
                    key = field.getName();
                }
                try {
                    value = String.valueOf(field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(key) && value != null) {
                    map.put(key, value);
                }
            }
        }
        return map;
    }
}
