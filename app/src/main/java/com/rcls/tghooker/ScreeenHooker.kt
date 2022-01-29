package com.rcls.tghooker

import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

class ScreenHooker : IXposedHookLoadPackage {
    override fun handleLoadPackage(loadPackageParam: LoadPackageParam) {
        val androidutilities = XposedHelpers.findClassIfExists("org.telegram.messenger.AndroidUtilities", loadPackageParam.classLoader)
        if (androidutilities != null) {
            XposedBridge.hookAllMethods(androidutilities, "setFlagSecure", XC_MethodReplacement.returnConstant(null))
        }
    }
}