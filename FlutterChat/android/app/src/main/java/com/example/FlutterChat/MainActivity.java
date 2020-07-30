package com.example.FlutterChat;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class MainActivity extends FlutterActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PluginChatPlugin.registerWith(registrarFor("io.flutter.plugins.PluginChatPlugin"),this);
    }
}
