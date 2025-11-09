package org.kycb.skyKYCbCompass.tag;

import org.bukkit.Location;

public class CmpTag {
    private final String type;
    private final String name;
    private final double overgrowthRadius;
    private final double detectionRadius;
    private Location location;

    public CmpTag(String type, String name, double detectionRadius, double overgrowthRadius,  Location location) {
        this.type = type;
        this.name = name;
        this.detectionRadius = detectionRadius;
        this.overgrowthRadius = overgrowthRadius;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getDetectionRadius() {
        return overgrowthRadius + detectionRadius;
    }

    public double getOvergrowthRadius() {
        return overgrowthRadius;
    }

    public Location getLocation() {
        return location;
    }

    void setMarkerLocation(Location newLocation) {
        this.location = newLocation;
    }

    public char getMarkerSymbol() {
        char markerSymbol = '?';
        switch (type) {
            case "villages" -> markerSymbol = 'À'; // Деревни. Код: \u00C0
            case "dwemerRuins" -> markerSymbol = 'Á'; // Двемерские руины. Код: \u00C1
            case "attractions" -> markerSymbol = 'Â'; // Достопримечательности. Код: \u00C2
            case "campsImperial" -> markerSymbol = 'Ã'; // Имперские лагеря. Код: \u00C3
            case "stonesConstellations" -> markerSymbol = 'Ä'; // Камни созвездий. Код: \u00C4
            case "stables" -> markerSymbol = 'Å'; // Конюшни. Код: \u00C5
            case "campsStormBrothers" -> markerSymbol = 'Æ'; // Лагерь Братьев Бури. Код: \u00C6
            case "campsGiants" -> markerSymbol = 'Ç'; // Лагерь великанов. Код: \u00C7
            case "sawmills" -> markerSymbol = 'È'; // Лесопилка. Код: \u00C8
            case "lairsDragons" -> markerSymbol = 'É'; // Логово драконов. Код: \u00C9
            case "nordicTowers" -> markerSymbol = 'Ê'; // Нордские башни. Код: \u00CA
            case "nordicRuins" -> markerSymbol = 'Ë'; // Нордские руины. Код: \u00CB
            case "orkFortresses" -> markerSymbol = 'Ì'; // Орочья крепость. Код: \u00CC
            case "caves" -> markerSymbol = 'Í'; // Пещеры. Код: \u00CD
            case "glades" -> markerSymbol = 'Î'; // Поляны. Код: \u00CE
            case "settlements" -> markerSymbol = 'Ï'; // Поселения. Код: \u00CF
            case "groves" -> markerSymbol = 'Ð'; // Рощи. Код: \u00D0
            case "ruins" -> markerSymbol = 'Ñ'; // Руины. Код: \u00D1
            case "signalTowers" -> markerSymbol = 'Ò'; // Сигнальные башни. Код: \u00D2
            case "farms" -> markerSymbol = 'Ó'; // Фермы. Код: \u00D3
            case "farms (with a mill)" -> markerSymbol = 'Ô'; // Фермы (с мельницей). Код: \u00D4
            case "forts" -> markerSymbol = 'Õ'; // Форты. Код: \u00D5
            case "huts" -> markerSymbol = 'Ù'; // Хижины. Кож: \u00D9
            case "mine" -> markerSymbol = 'Ú'; // Шахты. Код: \u00DA
        }
        return markerSymbol;
    }
}
