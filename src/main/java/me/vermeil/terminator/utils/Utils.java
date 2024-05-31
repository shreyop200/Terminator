/**
 * This utility class provides methods for colorizing strings.
 */
package me.vermeil.terminator.utils;

import org.bukkit.ChatColor;

public class Utils {

    /**
     * Colorizes a string by replacing color codes with their corresponding colors.
     *
     * @param string The string to colorize.
     * @return The colorized string.
     */
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
