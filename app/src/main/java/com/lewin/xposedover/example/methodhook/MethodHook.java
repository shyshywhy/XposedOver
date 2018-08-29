package com.lewin.xposedover.example.methodhook;

import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Lewin on 2018/8/29.
 */
public class MethodHook implements IXposedHookLoadPackage{



    /**
     * This method is called when an app is loaded. It's called very early, even before
     * {@link Application#onCreate} is called.
     * Modules can set up their app-specific hooks here.
     *
     * @param lpparam Information about the app.
     * @throws Throwable Everything the callback throws is caught and logged.
     */
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log(
                "package : " + lpparam.packageName +
                "process : " + lpparam.processName
        );
        if (lpparam.processName.equals("")) {
            XposedBridge.log("hook start");

//            Map<List<Class<?>>, Class<?>> proxyCache;
//            Field proxyCacheField = XposedHelpers.findField(ClassLoader.class, "proxyCache");
//            proxyCache = proxyCacheField.get()

//            XposedHelpers.findAndHookMethod(TextView.class, "setText", );

            XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", String.class,
                    new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("param" + param.args[0]);
                }
            });
        }
    }
}
