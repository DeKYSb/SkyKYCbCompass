package org.kycb.skyKYCbCompass.tag;

import org.bukkit.Location;

public class CompassMarker {
    private final String name;
    private final Location location;

    public CompassMarker(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
