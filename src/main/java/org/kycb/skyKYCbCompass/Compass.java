package org.kycb.skyKYCbCompass;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.kycb.skyKYCbCompass.tag.CmpTag;
import org.kycb.skyKYCbCompass.tag.MarkerManager;

public class Compass {

    // Углы сторон света в Minecraft
    public static final float NORTH_ANGLE = 180.0f;   // Север = 180°
    public static final float SOUTH_ANGLE = 0.0f;     // Юг = 0° (или 360°)
    public static final float EAST_ANGLE  = 270.0f;  // Восток = 270°
    public static final float WEST_ANGLE  = 90.0f;   // Запад = 90°

    public static final int BAR_LENGTH = 29; // Символов
    public static final float[] ANGLES = {NORTH_ANGLE, SOUTH_ANGLE, EAST_ANGLE, WEST_ANGLE};
    public static final char[] SYMBOLS = {'С', 'Ю', 'В', 'З'};
    public static final float VISIBILITY_ANGLE = 90.0f;
    public static final int MAX_POS = BAR_LENGTH - 1;
    public static final double MAX_DETECTION_DISTANCE = 100.0; // Блок
    private static final float POSITION_FACTOR = MAX_POS / (2 * VISIBILITY_ANGLE);

    private final StringBuilder barText = new StringBuilder();
    private final Player player;
    private final BossBar bossBar;
    private String currentBarText = "";

    public Compass(Player player) {
        this.player = player;
        this.bossBar = Bukkit.createBossBar(
                defaultCompass().toString(),
                BarColor.WHITE,
                BarStyle.SOLID
        );
        bossBar.addPlayer(player);
    }

    public void updateCompass(){
        String newText = defaultCompass().toString();
        if (!newText.equals(currentBarText)) {
            bossBar.setTitle(newText);
            currentBarText = newText;
        }
    }

private StringBuilder defaultCompass() {
    Location location = player.getLocation();
    float yaw = normalizeAngle(location.getYaw());

    barText.setLength(0);
    barText.append("=".repeat(BAR_LENGTH));

    drawCardinalDirections(yaw);

    drawMarkers(location, yaw);

    return barText;
}

    private void drawCardinalDirections(float yaw) {
        for (int i = 0; i < ANGLES.length; i++) {
            float targetAngle = normalizeAngle(ANGLES[i]);
            char symbol = SYMBOLS[i];

            float offset = calculateOffset(targetAngle, yaw);
            if (Math.abs(offset) <= VISIBILITY_ANGLE) {
                int pos = calculatePosition(offset);
                barText.setCharAt(pos, symbol);
            }
        }
    }

    private void drawMarkers(Location playerLoc, float yaw) {
        SkyKYCbCompass plugin = SkyKYCbCompass.getPlugin(SkyKYCbCompass.class);
        MarkerManager markerManager = plugin.getMarkerManager();

        for (CmpTag marker : markerManager.getAllInvestigatedMarkers().values()) {
            Location markerLoc = marker.getLocation();

            // Проверяем расстояние
            double distance = playerLoc.distance(markerLoc);
            if (distance < marker.getRadius() || distance > (marker.getRadius() + MAX_DETECTION_DISTANCE)) {
                continue;
            }

            // Рассчитываем направление на метку
            float markerYaw = calculateYawTo(playerLoc, markerLoc);
            float offset = calculateOffset(markerYaw, yaw);

            if (Math.abs(offset) <= VISIBILITY_ANGLE) {
                int pos = calculatePosition(offset);

                // Заменяем символ, если позиция свободна или это сторона света
                if (barText.charAt(pos) == '=' ||
                        isCardinalSymbol(barText.charAt(pos))) {
                    barText.setCharAt(pos, marker.getMarkerSymbol());
                }
            }
        }
    }

    private boolean isCardinalSymbol(char c) {
        for (char symbol : SYMBOLS) {
            if (c == symbol) return true;
        }
        return false;
    }

    private float calculateYawTo(Location from, Location to) {
        double dx = to.getX() - from.getX();
        double dz = to.getZ() - from.getZ();

        float yaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90f;
        return normalizeAngle(yaw);
    }

    private float normalizeAngle(float angle) {
        return (angle % 360 + 360) % 360;
    }

    private float calculateOffset(float target, float current) {
        float offset = (target - current + 360f) % 360f;
        if (offset >= 180f) offset -= 360f;
        return offset;
    }

    private int calculatePosition(float offset) {
        int pos = Math.round((offset + VISIBILITY_ANGLE) * POSITION_FACTOR);
        return Math.max(0, Math.min(pos, MAX_POS));
    }
}
