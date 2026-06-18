package com.flxholle.quicktiles.intent_tiles;

import android.os.Bundle;

import com.flxholle.quicktiles.abstract_tiles.BaseTileService;

public class OpenVolumePanelTileService extends BaseTileService {

    @Override
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("open_volume_panel", true);
        openMainActivity(bundle);
    }

    @Override
    public void reset() {
    }
}
