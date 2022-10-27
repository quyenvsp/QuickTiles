package com.flxholle.quicktiles;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.flxholle.quicktiles.utils.CustomAccessibilityService;
import com.flxholle.quicktiles.utils.GrantPermissionDialogs;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if ((extras == null || extras.getString("OPEN_SETTING") == null) && sharedPreferences.getBoolean("lock_screen", false) && GrantPermissionDialogs.hasAccessibilityServicePermission(this)) {
            startService(new Intent(this, CustomAccessibilityService.class).setAction(CustomAccessibilityService.LOCK_SCREEN));
            finishAndRemoveTask();
            return;
        }
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}