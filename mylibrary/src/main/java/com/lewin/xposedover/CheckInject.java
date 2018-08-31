package com.lewin.xposedover;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: CheckInject
 * @Description: java类作用描述
 * @Author: haiyang_tan
 * @Date: 2018/8/31 上午11:03
 */
public class CheckInject {

    private static final String TAG = "CheckInject";

    private static final String XPOSED_HELPERS_CLASS_NAME = "de.robv.android.xposed.XposedHelpers";

    private static final String[] KEY_WORDS = {
            "textview",
            "edittext"
    };

    public static byte checkXposedInject() {

    }


    private static byte checkKeyInField(Object cls, String fieldName) {

        Set keySet = null;

        try {
            Field field = cls.getClass().getField(fieldName);
            keySet = ((Map)field.get(cls)).keySet();
        } catch (NoSuchFieldException ignore) {

        } catch (IllegalAccessException ignore) {

        }
        if (keySet == null || keySet.isEmpty()) {
            return 0;
        }

        Iterator it = keySet.iterator();
        if (it.hasNext()) {
            String key = (String) it.next();

        }
    }

    /**
     * 检查字符串中是否包含关键字
     * @param name 需要被检查的字符
     * @return true 包含 false 不包含
     */
    private static boolean checkKeyWords(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        String nameCaseToLow = name.toLowerCase();
        for (String keyWord : KEY_WORDS) {
            if (nameCaseToLow.contains(keyWord)) {
                return true;
            }
        }
        return false;
    }
}
