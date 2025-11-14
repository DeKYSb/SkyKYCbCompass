package org.kycb.skyKYCbCompass.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.kycb.skyKYCbCompass.tag.MarkerManager;

import java.util.ArrayList;
import java.util.List;

public record CmpCompleter(MarkerManager markerManager) implements TabCompleter {

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return List.of(
                    "createmarker",
                    "movemarker",
                    "deletemarker",
                    "help",
                    "list"
            );
        }

        if (args.length == 2 && (args[0].equals("deletemarker") || args[0].equals("movemarker"))) {
            return new ArrayList<>(markerManager.keyInvestigatedMarkerSet());
        }

        if (args.length == 3 && args[0].equals("movemarker")) {
            return List.of("<x>");
        }

        if (args.length == 4 && args[0].equals("movemarker")) {
            return List.of("<y>");
        }

        if (args.length == 5 && args[0].equals("movemarker")) {
            return List.of("<z>");
        }

        if (args.length == 2 && args[0].equals("createmarker")) {
            return List.of(
                    "À<Деревня>",
                    "Á<Двемерсике-Руины>",
                    "Â<Достопримечательности>",
                    "Ã<Имперский-Лагерь>",
                    "Ä<Камни-Хранители>",
                    "Å<Конюшни>",
                    "Æ<Лагерь-Братьев-Бури>",
                    "Ç<Лагерь-Великанов>",
                    "È<Лесопилка>",
                    "É<Логово-Дракона>",
                    "Ê<Нордские-Башни>",
                    "Ë<Нордские-Руины>",
                    "Ì<Орочья-Крепость>",
                    "Í<Пещеры>",
                    "Î<Поляны>",
                    "Ï<Поселения>",
                    "Ð<Рощи>",
                    "Ñ<Руины>",
                    "Ò<Сигнальные-Башни>",
                    "Ó<Маленькая-Ферма>",
                    "Ô<Большая-Ферма>",
                    "Õ<Форты>",
                    "Ù<Хижины>",
                    "Ú<Шахты>"
            );
        }

        if (args.length == 3 && args[0].equals("createmarker")) {
            return List.of("<название метки>");
        }

        if (args.length == 4 && args[0].equals("createmarker")) {
            return List.of("<радиус обнаружения>");
        }

        if (args.length == 5 && args[0].equals("createmarker")) {
            return List.of("<радиус области>");
        }

        if (args.length == 6 && args[0].equals("createmarker")) {
            return List.of("<x>");
        }

        if (args.length == 7 && args[0].equals("createmarker")) {
            return List.of("<y>");
        }

        if (args.length == 8 && args[0].equals("createmarker")) {
            return List.of("<z>");
        }

        return List.of();
    }
}
