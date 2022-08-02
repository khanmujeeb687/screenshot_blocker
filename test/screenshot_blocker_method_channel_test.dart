import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:screenshot_blocker/screenshot_blocker_method_channel.dart';

void main() {
  MethodChannelScreenshotBlocker platform = MethodChannelScreenshotBlocker();
  const MethodChannel channel = MethodChannel('screenshot_blocker');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
