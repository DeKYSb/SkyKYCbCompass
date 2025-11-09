package org.kycb.skyKYCbCompass.tag;

import org.bukkit.Location;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarkerManager {

    private final Map<String, CmpTag> investigatedMarkers = new HashMap<>();

    //Исследованные
    public void addInvestigatedMarker(String type, String name, double detectionRadius, double overgrowthRadius, Location location) {
        investigatedMarkers.put(name, new CmpTag(type, name, detectionRadius, overgrowthRadius, location));
    }

    public void removeInvestigatedMarker(String name) {
        investigatedMarkers.remove(name);
    }

    public void updateInvestigatedMarkerLocation(String name, Location newLocation) {
        CmpTag marker = investigatedMarkers.get(name);
        marker.setMarkerLocation(newLocation);
    }

    public boolean hasInvestigatedMarker(String name){
        return investigatedMarkers.containsKey(name);
    }

    public Set<String> keyInvestigatedMarkerSet(){
        return investigatedMarkers.keySet();
    }

    public Map<String, CmpTag> getAllInvestigatedMarkers() {
        return Collections.unmodifiableMap(investigatedMarkers);
    }


}
