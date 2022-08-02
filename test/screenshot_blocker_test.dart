import 'package:flutter_test/flutter_test.dart';
import 'package:screenshot_blocker/screenshot_blocker.dart';
import 'package:screenshot_blocker/screenshot_blocker_platform_interface.dart';
import 'package:screenshot_blocker/screenshot_blocker_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockScreenshotBlockerPlatform 
    with MockPlatformInterfaceMixin
    implements ScreenshotBlockerPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<bool> disableScreenShots() {
    // TODO: implement disableScreenShots
    throw UnimplementedError();
  }

  @override
  Future<bool> enableScreenShots() {
    // TODO: implement enableScreenShots
    throw UnimplementedError();
  }
}

void main() {
  final ScreenshotBlockerPlatform initialPlatform = ScreenshotBlockerPlatform.instance;

  test('$MethodChannelScreenshotBlocker is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelScreenshotBlocker>());
  });

  test('getPlatformVersion', () async {
    ScreenshotBlocker screenshotBlockerPlugin = ScreenshotBlocker();
    MockScreenshotBlockerPlatform fakePlatform = MockScreenshotBlockerPlatform();
    ScreenshotBlockerPlatform.instance = fakePlatform;
  
    expect(await screenshotBlockerPlugin.getPlatformVersion(), '42');
  });
}
