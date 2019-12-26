package com.example.sopcode.system_promise_summary.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationManagerCompat;

import com.example.sopcode.system_promise_summary.dialog.OpenNotifyDialog;
import com.example.sopcode.utils.AppUtils;

public class NotificationsUtils {

    public static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return NotificationManagerCompat.from(context).getImportance() != NotificationManager.IMPORTANCE_NONE;
        }
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public static void openPermissionSetting(final Context context) {
        try {
            FragmentActivity activity = (FragmentActivity) context;
            final OpenNotifyDialog dialog = new OpenNotifyDialog();
            dialog.show(activity.getSupportFragmentManager());
            dialog.setCallBack(new OpenNotifyDialog.IDialogCallBack() {
                @Override
                public void sure() {
                    dialog.dismiss();
                    Intent localIntent = new Intent();
                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= 9) {
                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        localIntent.setData(Uri.fromParts("package", AppUtils.getPackageName(context), null));
                    } else if (Build.VERSION.SDK_INT <= 8) {
                        localIntent.setAction(Intent.ACTION_VIEW);

                        localIntent.setClassName("com.android.settings",
                                "com.android.settings.InstalledAppDetails");

                        localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                AppUtils.getPackageName(context));
                    }
                    context.startActivity(localIntent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
