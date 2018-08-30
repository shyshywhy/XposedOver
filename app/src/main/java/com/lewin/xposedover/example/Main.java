package com.lewin.xposedover.example;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @ClassName: Main
 * @Description: java类作用描述
 * @Author: haiyang_tan
 * @Date: 2018/8/30 下午2:39
 */
public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        HotXposed.hook(HookerDispatcher.class, lpparam);
    }
}
