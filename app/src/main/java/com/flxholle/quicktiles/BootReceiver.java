package com.flxholle.quicktiles;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.service.quicksettings.TileService;
import android.util.ArrayMap;

import androidx.preference.PreferenceManager;

import java.util.Map;
import java.util.Objects;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), Intent.ACTION_BOOT_COMPLETED)) {
            // Need update tile after boot
            ArrayMap<String, Class<?>> preferencesServices = SettingsActivity.getPreferenceService();
            Map<String, ?> preferences = PreferenceManager.getDefaultSharedPreferences(context).getAll();
            PackageManager pm = context.getPackageManager();
            for (Map.Entry<String, Class<?>> entry : preferencesServices.entrySet()) {
                Object switchPreference = preferences.get(entry.getKey());
                if (switchPreference != null && switchPreference.equals(Boolean.TRUE)) {
                    final ComponentName serviceClass = new ComponentName(context, entry.getValue());
                    pm.setComponentEnabledSetting(serviceClass,
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                    TileService.requestListeningState(context, serviceClass);
                }
            }
        }
    }
}

