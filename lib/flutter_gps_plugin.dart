import 'dart:async';

import 'package:flutter/services.dart';

class FlutterGpsPlugin {
  static const MethodChannel _channel =
      const MethodChannel('flutter_gps_plugin');

  static Future<bool> get isOpen async {
    final bool isOpen = await _channel.invokeMethod('isOpen');
    return isOpen;
  }
}
