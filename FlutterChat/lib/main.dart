import 'package:flutter/material.dart';

import 'plugin_chat.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  dynamic eventStr="";

  @override
  void initState() {
    super.initState();
    initEventChannel();
  }

   void initEventChannel(){
    PluginChat.eventChannel.receiveBroadcastStream().listen((event) {
      setState(() {
        eventStr = event;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('收到本地消息 数量 $eventStr"'),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            PluginChat.startLogin();
          },
          child: Text("跳转原生聊天页面"),
        ),
      ),
    );
  }
}
