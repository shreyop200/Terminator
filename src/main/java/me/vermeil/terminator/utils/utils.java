package me.vermeil.terminator.utils;

import org.bukkit.ChatColor;

public class utils {
    /**
     * util method for color codes
     *
     * @param string the string that we will apply the color
     * @return returns a {@link String string}
     */
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
