/**
 * @description {}
 * @author Zheng Zhijun
 * @time 2020/7/30
 */
import 'dart:async';

import 'package:flutter/services.dart';

class PluginChat {
  static const MethodChannel _channel = const MethodChannel('plugin_chat');
  static const EventChannel eventChannel = const EventChannel('plugin_chat_event');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<bool> startLogin() async {
    final bool start = await _channel.invokeMethod('startLogin');
    return start;
  }

  static Future<bool> startIm() async {
    final bool start = await _channel.invokeMethod('startIm');
    return start;
  }

  static Future<bool> startCallVideo() async {
    final bool start = await _channel.invokeMethod('startCallVideo');
    return start;
  }
}