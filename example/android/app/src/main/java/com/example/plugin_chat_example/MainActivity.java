package com.example.plugin_chat_example;

import android.os.Bundle;

import androidx.annotation.Nullable;
import io.flutter.app.FlutterActivity;
import io.flutter.plugins.PluginChatPlugin;

public class MainActivity extends FlutterActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PluginChatPlugin.registerWith(registrarFor("io.flutter.plugins.PluginChatPlugin"));
    }
}
