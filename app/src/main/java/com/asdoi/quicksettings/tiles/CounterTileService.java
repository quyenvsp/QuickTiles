package com.asdoi.quicksettings.tiles;

import android.service.quicksettings.Tile;

import com.asdoi.quicksettings.tilesUtils.CounterSharedPref;
import com.asdoi.quicksettings.utils.BaseTileService;

public class CounterTileService extends BaseTileService {

    @Override
    public void onStartListening() {
        updateTileResources();
    }

    @Override
    public void onClick() {
        CounterSharedPref.triggerCounter(this);
        updateTileResources();
    }

    private void updateTileResources() {
        Tile tile = getQsTile();
        tile.setLabel("" + CounterSharedPref.getCounter(this));
        tile.updateTile();
    }

    @Override
    public void reset() {
        CounterSharedPref.resetCounter(this);
    }
}
