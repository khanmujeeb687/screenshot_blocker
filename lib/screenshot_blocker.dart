
import 'screenshot_blocker_platform_interface.dart';

class ScreenshotBlocker {
  Future<String?> getPlatformVersion() {
    return ScreenshotBlockerPlatform.instance.getPlatformVersion();
  }

  static Future<bool> disableScreenShots() async{
    return ScreenshotBlockerPlatform.instance.disableScreenShots();
  }

  static Future<bool> enableScreenShots() async{
    return ScreenshotBlockerPlatform.instance.enableScreenShots();
  }
}
