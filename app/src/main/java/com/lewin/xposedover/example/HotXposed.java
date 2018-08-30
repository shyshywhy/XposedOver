package com.lewin.xposedover.example;

import java.io.File;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @ClassName: HotXposed
 * @Description: java类作用描述
 * @Author: haiyang_tan
 * @Date: 2018/8/30 下午3:56
 */
public class HotXposed {
    public HotXposed() {
    }

    public static void hook(Class clazz, XC_LoadPackage.LoadPackageParam lpparam) throws Exception {
        String packageName = clazz.getName().replace("." + clazz.getSimpleName(), "");
        File apkFile = getApkFileFor16(packageName);
        if (!apkFile.exists()) {
            XposedBridge.log( "apk file not found !!!");
        } else {
            XposedBridge.log( "apk file found success !!!");
            filterNotify(lpparam);
            PathClassLoader classLoader = new PathClassLoader(apkFile.getAbsolutePath(), lpparam.getClass().getClassLoader());
            XposedHelpers.callMethod(classLoader.loadClass(clazz.getName()).newInstance(), "dispatch", new Object[]{lpparam});
        }
    }

    private static void filterNotify(XC_LoadPackage.LoadPackageParam lpparam) throws ClassNotFoundException {
        if ("de.robv.android.xposed.installer".equals(lpparam.packageName)) {
            XposedHelpers.findAndHookMethod(lpparam.classLoader.loadClass("de.robv.android.xposed.installer.util.NotificationUtil"), "showModulesUpdatedNotification", new Object[]{new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(new Object());
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            }});
        }

    }

    private static File getApkFile(String packageName) {
        String filePath = String.format("/data/app/%s-%s/base.apk", packageName, 1);
        File apkFile = new File(filePath);
        if (!apkFile.exists()) {
            filePath = String.format("/data/app/%s-%s/base.apk", packageName, 2);
            apkFile = new File(filePath);
        }

        return apkFile;
    }

    private static File getApkFileFor16(String packageName) {
        String filePath = String.format("/data/app/%s-%d.apk", packageName, 1);
        File apkFile = new File(filePath);
        if (!apkFile.exists()) {
            filePath = String.format("/data/app/%s-%d.apk", packageName, 2);
            apkFile = new File(filePath);
        }

        return apkFile;
    }
}

