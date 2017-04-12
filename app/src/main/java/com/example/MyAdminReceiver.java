package com.example;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;


public class MyAdminReceiver extends DeviceAdminReceiver{

    Context context;

    public MyAdminReceiver(){

    }

    public MyAdminReceiver(Context context) {
      this.context = context;
    }

    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        showToast(context, "Sample Device Admin: Unlock failed");
        Vibrator v = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        // Vibrate for 1 seconds
        v.vibrate(1000);

        DevicePolicyManager mgr = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        int no = mgr.getCurrentFailedPasswordAttempts();

        if (no >= 3) {
            showToast(context, "3 times exceeded");
            Intent i1 = new Intent (context, camera.class);
            i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i1);
        }
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        showToast(context, "Welcome Device Admin");
    }
}
