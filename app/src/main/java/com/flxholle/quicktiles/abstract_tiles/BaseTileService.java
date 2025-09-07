package com.flxholle.quicktiles.abstract_tiles;

import android.content.ComponentName;
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
}
