package cn.wildfire.chat.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.plugin_chat_example.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;

import cn.wildfire.chat.app.test.ExampleAudioInputExt;
import cn.wildfire.chat.app.test.RedMessageContent;
import cn.wildfire.chat.app.test.RedMessageContentViewHolder;
import cn.wildfire.chat.kit.Config;
import cn.wildfire.chat.kit.WfcUIKit;
import cn.wildfire.chat.kit.conversation.ext.core.ConversationExtManager;
import cn.wildfire.chat.kit.conversation.message.viewholder.MessageViewHolderManager;
import cn.wildfire.chat.kit.third.location.viewholder.LocationMessageContentViewHolder;
import com.example.plugin_chat_example.R;
import cn.wildfirechat.push.PushService;
import cn.wildfirechat.remote.ChatManager;


public class MyApp extends BaseApp {


    // 一定记得替换为你们自己的，ID请从BUGLY官网申请。关于BUGLY，可以从BUGLY官网了解，或者百度。
    public static String BUGLY_ID = "f53e146712";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sp=getSharedPreferences("config_ip",MODE_PRIVATE);
        String ip_host = sp.getString("ip_host","10.0.0.21");
        Config.setConfig(ip_host);

        Config.init(this);

        Config.validateConfig();

        // bugly，务必替换为你自己的!!!
        if ("wildfirechat.cn".equals(Config.IM_SERVER_HOST)) {
            CrashReport.initCrashReport(getApplicationContext(), BUGLY_ID, false);
        }
        // 只在主进程初始化，否则会导致重复收到消息
        if (getCurProcessName(this).equals(BuildConfig.APPLICATION_ID)) {
            // 如果uikit是以aar的方式引入 ，那么需要在此对Config里面的属性进行配置，如：
            // Config.IM_SERVER_HOST = "im.example.com";
            WfcUIKit wfcUIKit = WfcUIKit.getWfcUIKit();
            wfcUIKit.init(this);
            wfcUIKit.setAppServiceProvider(AppService.Instance());
            PushService.init(this, BuildConfig.APPLICATION_ID);
            MessageViewHolderManager.getInstance().registerMessageViewHolder(LocationMessageContentViewHolder.class, R.layout.conversation_item_location_send, R.layout.conversation_item_location_send);
            MessageViewHolderManager.getInstance().registerMessageViewHolder(RedMessageContentViewHolder.class, R.layout.conversation_item_red_send, R.layout.conversation_item_red_receive);
            ChatManager.Instance().registerMessageContent(RedMessageContent.class);
            ConversationExtManager.getInstance().registerExt(ExampleAudioInputExt.class);
            setupWFCDirs();
        }
    }

    private void setupWFCDirs() {
        File file = new File(Config.VIDEO_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(Config.AUDIO_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(Config.FILE_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(Config.PHOTO_SAVE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
            .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
            .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
