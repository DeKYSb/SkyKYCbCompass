package org.kycb.skyKYCbCompass.tag;

import org.bukkit.Location;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarkerManager {

    private final Map<String, CmpTag> investigatedMarkers = new HashMap<>();
    private final Map<String, CmpTag> unexploredMarkers = new HashMap<>();

    //Исследованные
    public void addInvestigatedMarker(String type, String name, double radius, Location location) {
        investigatedMarkers.put(name, new CmpTag(type, name, radius, location));
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

    //Неизвестные
    public void addUnexploredMarker(String name, CmpTag cmpTag) {
        unexploredMarkers.put(name, cmpTag);
    }

    public void removeUnexploredMarker(String name) {
        unexploredMarkers.remove(name);
    }

    public boolean hasUnexploredMarker(String name){
        return unexploredMarkers.containsKey(name);
    }

    public Set<String> keyUnexploredMarkerSet(){
        return unexploredMarkers.keySet();
    }

    public Map<String, CmpTag> getAllUnexploredMarkers() {
        return Collections.unmodifiableMap(unexploredMarkers);
    }

}
