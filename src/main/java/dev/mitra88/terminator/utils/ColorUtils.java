package dev.mitra88.terminator.utils;

import org.bukkit.ChatColor;

public class ColorUtils {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
