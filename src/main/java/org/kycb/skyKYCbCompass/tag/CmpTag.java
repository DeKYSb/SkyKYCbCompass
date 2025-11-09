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
            case "À<Деревня>" -> markerSymbol = 'À';               // Код: u00C0
            case "Á<Двемерсике-Руины>" -> markerSymbol = 'Á';      // Код: u00C1
            case "Â<Достопримечательности>" -> markerSymbol = 'Â'; // Код: u00C2
            case "Ã<Имперский-Лагерь>" -> markerSymbol = 'Ã';      // Код: u00C3
            case "Ä<Камни-Хранители>" -> markerSymbol = 'Ä';       // Код: u00C4
            case "Å<Конюшни>" -> markerSymbol = 'Å';               // Код: u00C5
            case "Æ<Лагерь-Братьев-Бури>" -> markerSymbol = 'Æ';   // Код: u00C6
            case "Ç<Лагерь-Великанов>" -> markerSymbol = 'Ç';      // Код: u00C7
            case "È<Лесопилка>" -> markerSymbol = 'È';             // Код: u00C8
            case "É<Логово-Дракона>" -> markerSymbol = 'É';        // Код: u00C9
            case "Ê<Нордские-Башни>" -> markerSymbol = 'Ê';        // Код: u00CA
            case "Ë<Нордские-Руины>" -> markerSymbol = 'Ë';        // Код: u00CB
            case "Ì<Орочья-Крепость>" -> markerSymbol = 'Ì';       // Код: u00CC
            case "Í<Пещеры>" -> markerSymbol = 'Í';                // Код: u00CD
            case "Î<Поляны>" -> markerSymbol = 'Î';                // Код: u00CE
            case "Ï<Поселения>" -> markerSymbol = 'Ï';             // Код: u00CF
            case "Ð<Рощи>" -> markerSymbol = 'Ð';                  // Код: u00D0
            case "Ñ<Руины>" -> markerSymbol = 'Ñ';                 // Код: u00D1
            case "Ò<Сигнальные-Башни>" -> markerSymbol = 'Ò';      // Код: u00D2
            case "Ó<Фермы>" -> markerSymbol = 'Ó';                 // Код: u00D3
            case "Ô<Фермы-(с-мельницей)>" -> markerSymbol = 'Ô';   // Код: u00D4
            case "Õ<Форты>" -> markerSymbol = 'Õ';                 // Код: u00D5
            case "Ù<Хижины>" -> markerSymbol = 'Ù';                // Кож: u00D9
            case "Ú<Шахты>" -> markerSymbol = 'Ú';                 // Код: u00DA
        }
        return markerSymbol;
    }
}
