package org.kycb.skyKYCbCompass;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.kycb.skyKYCbCompass.command.CompassCommand;
import org.kycb.skyKYCbCompass.command.CompassCompleter;
import org.kycb.skyKYCbCompass.handler.CompassListener;
import org.kycb.skyKYCbCompass.tag.CompassMarker;

import java.util.*;


public final class SkyKYCbCompass extends JavaPlugin {

    private final Map<String, CompassMarker> markers = new HashMap<>();

    public void addMarker(String name, Location location) {
        markers.put(name, new CompassMarker(location));
    }

    public void removeMarker(String name) {
        markers.remove(name);
    }

    public boolean hasMarker(String name){
        return markers.containsKey(name);
    }

    public Set<String> keySet(){
        return markers.keySet();
    }

    public Map<String, CompassMarker> getAllMarkers() {
        return Collections.unmodifiableMap(markers);
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("compass")).setExecutor(new CompassCommand(this));
        Objects.requireNonNull(getCommand("compass")).setTabCompleter(new CompassCompleter(this));
        getServer().getPluginManager().registerEvents(new CompassListener(), this);
    }

}
