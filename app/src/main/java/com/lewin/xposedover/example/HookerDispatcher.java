package com.lewin.xposedover.example;

import com.lewin.xposedover.example.methodhook.MethodHook;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @ClassName: HookerDispatcher
 * @Description: java类作用描述
 * @Author: haiyang_tan
 * @Date: 2018/8/30 下午2:41
 */
public class HookerDispatcher implements IHookerDispatcher{

    MethodHook hook = new MethodHook();

    @Override
    public void dispatch(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        try {
            hook.handleLoadPackage(loadPackageParam);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
