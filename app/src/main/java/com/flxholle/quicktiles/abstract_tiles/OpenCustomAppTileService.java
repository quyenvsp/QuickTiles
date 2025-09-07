package com.flxholle.quicktiles.abstract_tiles;

import android.content.Intent;
import android.service.quicksettings.Tile;

import com.flxholle.quicktiles.R;
import com.flxholle.quicktiles.utils.SelectApp;
import com.flxholle.quicktiles.utils.SharedPreferencesUtil;

public abstract class OpenCustomAppTileService extends IntentTileService {

    @Override
    public void onTileAdded() {
        super.onTileAdded();
        updateState();
    }

    @Override
    public void onStartListening() {
        updateState();
    }

    @Override
    public Intent createIntent() {
        String packageName = SharedPreferencesUtil.getCustomPackage(this, getPreferencesKey());
        if (packageName != null)
            return getPackageManager().getLaunchIntentForPackage(packageName);
        else {
            showDialog(SelectApp.selectApps(this, getPreferencesKey()));
            return getPackageManager().getLaunchIntentForPackage(getApplicationInfo().packageName);
        }
    }

    private void updateState() {
        Tile tile = getQsTile();
        String packageName = SharedPreferencesUtil.getCustomPackage(this, getPreferencesKey());
        if (packageName != null) {
            tile.setLabel(SharedPreferencesUtil.getCustomPackage(this, getLabelPreferenceKey()));
        } else {
            tile.setLabel(getString(R.string.custom_app));
        }
        tile.updateTile();
    }

    public abstract String getPreferencesKey();

    public abstract String getLabelPreferenceKey();
}
