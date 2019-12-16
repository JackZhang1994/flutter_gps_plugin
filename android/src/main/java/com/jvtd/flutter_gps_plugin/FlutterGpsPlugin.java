package com.jvtd.flutter_gps_plugin;

import android.content.Context;
import android.location.LocationManager;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterGpsPlugin
 */
public class FlutterGpsPlugin implements MethodCallHandler
{

  private static Context mContext;

  /**
   * Plugin registration.
   */
  public static void registerWith(Registrar registrar)
  {
    mContext = registrar.activeContext();
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_gps_plugin");
    channel.setMethodCallHandler(new FlutterGpsPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result)
  {
    if (call.method.equals("isOpen"))
    {
      //检测GPS是否开启
      boolean isOpen = isOpen();
      result.success(isOpen);
    } else
    {
      result.notImplemented();
    }
  }

  private boolean isOpen()
  {
    LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    // 确认GPS是否开启
    return gps || network;
  }

}
