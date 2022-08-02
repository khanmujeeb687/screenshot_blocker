package com.screenshot_blocker.screenshot_blocker;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.shim.ShimPluginRegistry;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;

/** ScreenshotBlockerPlugin */
public class ScreenshotBlockerPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  final private String ENABLE_SCREENSHOTS = "enableScreenShots";
  final private String DISABLE_SCREENSHOTS = "disableScreenShots";
  private Activity activity;


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "screenshot_blocker");
    channel.setMethodCallHandler(this);
  }



  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {

    switch(call.method) {
      case ENABLE_SCREENSHOTS:
        enableScreenShots(result);
        break;
      case DISABLE_SCREENSHOTS:
        disableScreenShots(result);
        break;
      default:
        result.notImplemented();
    }
  }

  private void disableScreenShots(Result result) {
    try{
      activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
      result.success(true);
    }catch (Exception e){
      result.success(false);
    }
  }

  private void enableScreenShots(Result result) {
    try{
      activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
      result.success(true);
    }catch (Exception e){
      result.success(false);
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    activity = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

  }

  @Override
  public void onDetachedFromActivity() {

  }
}
