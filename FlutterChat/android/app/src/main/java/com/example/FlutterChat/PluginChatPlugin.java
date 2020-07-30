package com.example.FlutterChat;

import android.content.Context;
import android.content.Intent;

import java.util.Arrays;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import cn.wildfire.chat.app.main.SplashActivity;
import cn.wildfire.chat.kit.conversationlist.ConversationListViewModel;
import cn.wildfire.chat.kit.conversationlist.ConversationListViewModelFactory;
import cn.wildfirechat.model.Conversation;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class PluginChatPlugin implements FlutterPlugin, MethodCallHandler, EventChannel.StreamHandler {
    private MethodChannel channel;
    private static EventChannel eventChannel;
    private static EventChannel.EventSink eventSink;
    private Context applicationContext;
    private static ConversationListViewModel conversationListViewModel;

    public PluginChatPlugin(Context context) {
        applicationContext = context.getApplicationContext();
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        applicationContext = flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "plugin_chat");
        eventChannel = new EventChannel(flutterPluginBinding.getBinaryMessenger(), "plugin_chat_event");
        channel.setMethodCallHandler(this);
        eventChannel.setStreamHandler(this);
    }

    public static void registerWith(Registrar registrar, FragmentActivity activity) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "plugin_chat");
        channel.setMethodCallHandler(new PluginChatPlugin(registrar.context()));

        eventChannel = new EventChannel(registrar.messenger(), "plugin_chat_event");
        eventChannel.setStreamHandler(new PluginChatPlugin(registrar.context()));


        conversationListViewModel=new ViewModelProvider(activity,new ConversationListViewModelFactory(Arrays.asList(Conversation.ConversationType.Single, Conversation.ConversationType.Group, Conversation.ConversationType.Channel), Arrays.asList(0))).get(ConversationListViewModel.class);
        conversationListViewModel.unreadCountLiveData().observe(activity, unreadCount -> {
            if (unreadCount != null && unreadCount.unread > 0) {
                sendNum(unreadCount.unread);
            } else {
                sendNum(0);
            }
        });

    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("startLogin")) {
            Intent intent = new Intent(applicationContext, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            applicationContext.startActivity(intent);
            result.success(true);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    public static void sendNum(int Num) {
        if(eventSink==null) return;
        eventSink.success("" + Num);
    }

    @Override
    public void onListen(Object arguments, EventChannel.EventSink events) {
        eventSink = events;

    }

    @Override
    public void onCancel(Object arguments) {
        eventSink = null;
    }
}
