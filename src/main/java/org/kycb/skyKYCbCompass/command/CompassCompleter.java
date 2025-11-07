package org.kycb.skyKYCbCompass.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kycb.skyKYCbCompass.SkyKYCbCompass;

import java.util.ArrayList;
import java.util.List;

public class CompassCompleter implements TabCompleter {

    private final SkyKYCbCompass plugin;

    public CompassCompleter(SkyKYCbCompass plugin) {
        this.plugin = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return List.of(
                    "setmarker",
                    "deletemarker",
                    "help",
                    "list"
            );
        }

        if (args.length == 2 && args[0].equals("setmarker")){
            return List.of("<название метки>");
        }

        if (args.length == 2 && args[0].equals("deletemarker")) {
            return new ArrayList<>(plugin.keySet());
        }

        if (args.length == 3 && args[0].equals("setmarker")) {
            return List.of("<x>");
        }

        if (args.length == 4 && args[0].equals("setmarker")) {
            return List.of("<y>");
        }

        if (args.length == 5 && args[0].equals("setmarker")) {
            return List.of("<z>");
        }

        return List.of();
    }
}
