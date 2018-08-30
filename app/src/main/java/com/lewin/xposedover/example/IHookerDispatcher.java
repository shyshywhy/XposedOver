package com.lewin.xposedover.example;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @ClassName: IHookerDispatcher
 * @Description: java类作用描述
 * @Author: haiyang_tan
 * @Date: 2018/8/30 下午3:57
 */
public interface IHookerDispatcher {
    void dispatch(XC_LoadPackage.LoadPackageParam var1);
}
