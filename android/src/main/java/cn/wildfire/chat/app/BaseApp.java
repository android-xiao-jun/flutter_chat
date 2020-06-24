package cn.wildfire.chat.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import androidx.multidex.MultiDex;
import io.flutter.app.FlutterApplication;

public class BaseApp extends FlutterApplication {

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文
    private static Application application;//上下文
    private static long mMainThreadId;//主线程id
    private static Handler mHandler;//主线程Handler

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        //对全局属性赋值
        mContext = getApplicationContext();
        mMainThreadId = android.os.Process.myTid();
        mHandler = new Handler();
    }

    public static Context getContext() {
        return mContext;
    }

    public static Application getApplication() {
        return application;
    }

    public static void setContext(Context mContext) {
        BaseApp.mContext = mContext;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }
}
