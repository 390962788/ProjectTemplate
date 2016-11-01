package com.guoyizeng.alipay;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Administrator on 2016/3/28.
 */
public class PermissionUtil {

    public static final int EXTERNAL_STORAGE_REQ_CODE = 10;
    public static final int READ_EXTERNAL_STORAGE = 9;

    public static boolean requestPermission(Activity context) {
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                return true;
            } else {
                //进行权限请求
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQ_CODE);
                //进行权限请求
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
                }
                return false;
            }
        }
        return true;
    }

    public static boolean requestPermission(Activity context, String permission, int requestCode) {
        //判断当前Activity是否已经获得了该权限
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
                ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);
                return true;
            } else {
                //进行权限请求
                ActivityCompat.requestPermissions(context, new String[]{permission}, requestCode);
                return false;
            }
        }
        return true;
    }

    public static boolean requestPermission(Activity context, String... permission) {
        //判断当前Activity是否已经获得了该权限
        for (int i = 0; i < permission.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                //如果App的权限申请曾经被用户拒绝过，就需要在这里跟用户做出解释
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission[i])) {
                    return true;
                } else {
                    //进行权限请求
                    ActivityCompat.requestPermissions(context, new String[]{permission[i]}, i);
                    //进行权限请求
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ActivityCompat.requestPermissions(context, new String[]{permission[i]}, i);
                    }
                    return false;
                }
            }
        }

        return true;
    }

    public static void checkPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(context)) {
                Uri selfPackageUri = Uri.parse("package:" + context.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, selfPackageUri);
                context.startActivity(intent);
            }
        }
    }
}
