package org.kycb.skyKYCbCompass;

import org.bukkit.plugin.java.JavaPlugin;
import org.kycb.skyKYCbCompass.command.CmpCommand;
import org.kycb.skyKYCbCompass.command.CmpCompleter;
import org.kycb.skyKYCbCompass.handler.CmpListener;
import org.kycb.skyKYCbCompass.tag.MarkerManager;

import java.util.*;


public final class SkyKYCbCompass extends JavaPlugin {

    private final MarkerManager markerManager = new MarkerManager();

    public MarkerManager getMarkerManager() {
        return markerManager;
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("compass")).setExecutor(new CmpCommand(markerManager));
        Objects.requireNonNull(getCommand("compass")).setTabCompleter(new CmpCompleter(markerManager));
        getServer().getPluginManager().registerEvents(new CmpListener(), this);
    }

}
