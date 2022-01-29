package com.rcls.tghooker
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(loadPackageParam: LoadPackageParam) {
        val webHook = XposedHelpers.findClassIfExists("org.telegram.messenger.BuildVars", loadPackageParam.classLoader)
        if (webHook != null) {
            XposedBridge.hookAllMethods(webHook, "isStandaloneApp", XC_MethodReplacement.returnConstant(0x1))
        }
        val messagesController = XposedHelpers.findClassIfExists("org.telegram.messenger.MessagesController", loadPackageParam.classLoader)
        if (messagesController != null) {
            XposedBridge.hookAllMethods(messagesController, "getSponsoredMessages", XC_MethodReplacement.returnConstant(null))
        }
    }
}