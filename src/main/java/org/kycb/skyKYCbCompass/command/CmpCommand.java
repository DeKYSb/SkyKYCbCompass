package org.kycb.skyKYCbCompass.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kycb.skyKYCbCompass.tag.MarkerManager;

public record CmpCommand(MarkerManager markerManager) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Только игроки могут использовать эту команду!");
            return true;
        }

        if (args.length == 0) {
            help(sender);
            return true;
        }

        switch (args[0]) {
            case "help" -> {
                help(sender);
                return true;
            }
            case "deletemarker" -> {
                deleteMarker(sender, args);
                return true;
            }
            case "list" -> {
                sender.sendMessage(markerManager.keyInvestigatedMarkerSet().toString());
                return true;
            }
            case "createmarker" -> {
                createMarker(sender, args, player);
                return true;
            }
            case "movemarker" -> {
                moveMarker(sender, args, player);
                return true;
            }
        }
        return false;
    }

    private void help(CommandSender sender) {
        sender.sendMessage("""
                /compass createmarker ... -> создать метку
                /compass deletemarker ... -> удалить метку
                /compass movemarker ... -> переместить метку
                /compass list -> список существующих меток""");
    }

    private void createMarker(CommandSender sender, String[] args, Player player) {
        int numberArgs = 8;

        if (args.length != numberArgs) {
            sender.sendMessage("Использование: /compass createmarker <тип маркера> <название метки> <радиус обнаружения> <радиус области> <x> <y> <z>");
            return;
        }

        double detectionRadius, overgrowthRadius, x, y, z;

        try {
            detectionRadius = Double.parseDouble(args[3]);
            overgrowthRadius = Double.parseDouble(args[4]);
            x = Double.parseDouble(args[5]);
            y = Double.parseDouble(args[6]);
            z = Double.parseDouble(args[7]);

        } catch (NumberFormatException e) {
            sender.sendMessage("Ошибка: радиусы и координаты должны быть числами!");
            return;
        }

        String type = args[1];
        String name = args[2];
        Location loc = new Location(player.getWorld(), x, y, z);


        if (markerManager.hasInvestigatedMarker(name)) {
            sender.sendMessage("Метка §a" + name + "§f уже существует.\n" +
                    "Доступные действия:\n" +
                    "/compass movemarker... (переместить)\n" +
                    "/compass deletemarker ... (удалить)");
        } else {
            markerManager.addInvestigatedMarker(type, name, detectionRadius, overgrowthRadius, loc);
            sender.sendMessage("Метка §a" + name + "§f установлена.");
        }
    }

    private void deleteMarker(CommandSender sender, String[] args) {
        int numberArgs = 2;

        if (args.length != numberArgs) {
            sender.sendMessage("Использование: /compass deletemarker <название метки>");
            return;
        }

        String name = args[1];


        if (markerManager.hasInvestigatedMarker(name)) {
            markerManager.removeInvestigatedMarker(name);
            sender.sendMessage("Метка §a" + name + "§f удалена.");
        } else {
            sender.sendMessage("Метки §a" + name + "§f не существует!");
        }
    }

    private void moveMarker(CommandSender sender, String[] args, Player player) {
        int numberArgs = 5;

        if (args.length != numberArgs) {
            sender.sendMessage("Использование: /compass movemarker <название метки> <x> <y> <z>");
            return;
        }

        double x, y, z;

        try {
            x = Double.parseDouble(args[2]);
            y = Double.parseDouble(args[3]);
            z = Double.parseDouble(args[4]);

        } catch (NumberFormatException e) {
            sender.sendMessage("Ошибка: координаты должны быть числами!");
            return;
        }

        String name = args[1];
        Location loc = new Location(player.getWorld(), x, y, z);


        if (markerManager.hasInvestigatedMarker(name)) {
            markerManager.updateInvestigatedMarkerLocation(name, loc);
            sender.sendMessage("Метка §a" + name + "§f перемещена.");
        } else {
            sender.sendMessage("Метки §a" + name + "§f не существует!");
        }
    }
}
