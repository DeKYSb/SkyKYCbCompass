package org.kycb.skyKYCbCompass.tag;

import org.bukkit.Location;

public class CmpTag {
    private final String type;
    private final String name;
    private final double radius;
    private Location location;

    public CmpTag(String type, String name, double radius, Location location) {
        this.type = type;
        this.name = name;
        this.radius = radius;
        this.location = location;
    }

    public double getRadius() {
        return radius;
    }

    public Location getLocation() {
        return location;
    }

    void setMarkerLocation(Location newLocation) {
        this.location = newLocation;
    }

    public char getMarkerSymbol() {
        char markerSymbol = ' ';
        switch (type) {
            case "villages" -> markerSymbol = 'Д';
            case "mine" -> markerSymbol = 'Ш';
        }
        return markerSymbol;
    }
}
