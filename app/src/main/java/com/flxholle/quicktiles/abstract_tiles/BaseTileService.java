package com.flxholle.quicktiles.abstract_tiles;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public abstract class BaseTileService extends TileService {

    //This should be called when the Tile gets disabled by the user
    public abstract void reset();

    // Called when the user adds your tile.
    @Override
    public void onTileAdded() {
        super.onTileAdded();
        TileService.requestListeningState(this, new ComponentName(this, this.getClass()));
        Tile tile = getQsTile();
        tile.setState(Tile.STATE_INACTIVE);
        tile.updateTile();
    }

    // Called when your app can update your tile.
    @Override
    public void onStartListening() {
        super.onStartListening();
        Tile tile = getQsTile();
        tile.setState(Tile.STATE_INACTIVE);
        tile.updateTile();
    }

    @SuppressLint("StartActivityAndCollapseDeprecated")
    public void openMainActivity(Bundle extras) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getApplicationInfo().packageName);
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (extras != null) {
                intent.putExtras(extras);
            }
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startActivityAndCollapse(intent);
            } else {
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );
                startActivityAndCollapse(pendingIntent);
            }
        }
    }
}
