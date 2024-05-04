package me.vermeil.terminator.builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * this class is responsible for the creation of items
 */
public class TerminatorBuilder {
    /**
     * creates a terminator itemStack
     * <p></p>
     * TODO: utilise the {@link me.vermeil.terminator.utils.utils#color(String) utils.color} method to add lore display name etc
     *
     * @return the terminator {@link ItemStack itemStack}
     */
    public static ItemStack giveTerminator() {
        ItemStack terminatorBow = new ItemStack(Material.BOW);
        ItemMeta meta = terminatorBow.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(ChatColor.LIGHT_PURPLE + "Hasty Terminator " + ChatColor.GOLD + "✪✪✪✪" + ChatColor.RED + "➎");

        List<String> lore = Arrays.asList(
                ChatColor.GRAY + "Gear Score: " + ChatColor.LIGHT_PURPLE + "1014 " + ChatColor.DARK_GRAY + "(3467)",
                ChatColor.GRAY + "Damage: " + ChatColor.RED + "371 " + ChatColor.YELLOW + "(+30) " + ChatColor.DARK_GRAY + "(+1,598)",
                ChatColor.GRAY + "Strength: " + ChatColor.RED + "110 " + ChatColor.YELLOW + "(+30) " + ChatColor.GOLD + "[+5] " + ChatColor.BLUE + "(+20) " + ChatColor.DARK_GRAY + "(+493.5)",
                ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+65% " + ChatColor.BLUE + "(+60%) " + ChatColor.DARK_GRAY + "(+100,75%)",
                ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+280% " + ChatColor.DARK_GRAY + "(+1,198,5%)",
                ChatColor.GRAY + "Bonus Attack Speed: " + ChatColor.RED + "+44% " + ChatColor.DARK_GRAY + "(+62%)",
                ChatColor.GRAY + "Shot Cooldown: " + ChatColor.GREEN + "0.5s",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Eater V " + ChatColor.BLUE + "Chance V, Cubism VI",
                ChatColor.BLUE + "Dragon Hunter V, Dragon Tracer V, Flame II",
                ChatColor.BLUE + "Impaling III, Infinite Quiver X, Overload V",
                ChatColor.BLUE + "Piercing I, Power VII, Punch II",
                ChatColor.BLUE + "Snipe IV, Vicious V",
                "",
                ChatColor.DARK_PURPLE + "◆ End Rune III",
                "",
                ChatColor.GRAY + "Shoots " + ChatColor.AQUA + "3 " + ChatColor.GRAY + "arrows at ones.",
                ChatColor.GRAY + "Can damage enderman.",
                "",
                ChatColor.RED + "Divides your " + ChatColor.BLUE + "☣ Crit Chance " + ChatColor.RED + "by 4!",
                "",
                ChatColor.GOLD + "Ability: Salvation" + ChatColor.YELLOW + " " + ChatColor.BOLD + "LEFT CLICK",
                ChatColor.GRAY + "Can be casted after landing " + ChatColor.GOLD + "3 " + ChatColor.GRAY + "hits.",
                ChatColor.GRAY + "Shoot a beam, penetrating up to " + ChatColor.YELLOW + "5",
                ChatColor.GRAY + "enemies.",
                ChatColor.GRAY + "The beam always crits.",
                ChatColor.DARK_GRAY + "Soulflow Cost: " + ChatColor.DARK_AQUA + "1⸎",
                "",
                ChatColor.LIGHT_PURPLE + "Shortbow: Instantly shoots!",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.MAGIC + "A" + ChatColor.RESET + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " MYTHIC DUNGEON BOW " + ChatColor.MAGIC + "A"
        );

        meta.setLore(lore);
        terminatorBow.setItemMeta(meta);

        return terminatorBow;
    }
}
