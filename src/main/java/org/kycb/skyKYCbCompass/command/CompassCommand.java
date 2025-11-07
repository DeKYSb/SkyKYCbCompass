package org.kycb.skyKYCbCompass.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kycb.skyKYCbCompass.SkyKYCbCompass;

public class CompassCommand implements CommandExecutor {

    private final SkyKYCbCompass plugin;

    public CompassCommand(SkyKYCbCompass plugin){
        this.plugin = plugin;
    }

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
            case "setmarker" -> {
                setMarker(sender, args, player);
                return true;
            }
            case "deletemarker" -> {
                deleteMarker(sender, args);
                return true;
            }
            case "list" -> {
                sender.sendMessage(plugin.keySet().toString());
                return true;
            }
        }
        return false;
    }

    private void help(CommandSender sender) {
        sender.sendMessage("""
                /compass setmarker <название метки> <x> <y> <z> -> установит метку
                /compass deletemarker <название метки> -> удалить метку
                /compass list -> список существующих меток""");
    }

    private void setMarker(CommandSender sender, String[] args, Player player) {
        int numberArgs = 5;

        if (args.length != numberArgs) {
            sender.sendMessage("Использование: /compass setmarker <название метки> <x> <y> <z>");
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

        if (plugin.hasMarker(name)) {
            plugin.addMarker(name, loc);
            sender.sendMessage("Метка §a" + name + "§f перенесена.");
        } else {
            plugin.addMarker(name, loc);
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

        if (plugin.hasMarker(name)) {
            plugin.removeMarker(name);
            sender.sendMessage("Метка §a" + name + "§f удалена!");
        } else {
            sender.sendMessage("Метки §a" + name + "§f не существует!");
        }
    }
}
