/**
 * This class provides a method to create a custom item called Terminator.
 */
package me.vermeil.terminator.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.vermeil.terminator.utils.Utils;
import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TerminatorBuilder {

    /**
     * Creates and returns the Terminator with specific attributes and enchantments.
     *
     * @return The customized Terminator.
     */
    public static ItemStack giveTerminator() {
        ItemStack terminatorBow = new ItemStack(Material.BOW);
        ItemMeta meta = terminatorBow.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(Utils.color("&dHasty Terminator &6✪✪✪✪&c➎"));

        List<String> lore = Arrays.asList(
                Utils.color("&7Gear Score: &d1014 &8(3467)"),
                Utils.color("&7Damage: &c371 &e(+30) &8(+1,598)"),
                Utils.color("&7Strength: &c110 &e(+30) &6[+5] &9(+20) &8(+493.5)"),
                Utils.color("&7Crit Chance: &c+65% &9(+60%) &8(+100,75%)"),
                Utils.color("&7Crit Damage: &c+280% &8(+1,198,5%)"),
                Utils.color("&7Bonus Attack Speed: &c+44% &8(+62%)"),
                Utils.color("&7Shot Cooldown: &a0.5s"),
                "",
                Utils.color("&d&lSoul Eater V &9Chance V, Cubism VI"),
                Utils.color("&9Dragon Hunter V, Dragon Tracer V, Flame II"),
                Utils.color("&9Impaling III, Infinite Quiver X, Overload V"),
                Utils.color("&9Piercing I, Power VII, Punch II"),
                Utils.color("&9Snipe IV, Vicious V"),
                "",
                Utils.color("&5◆ End Rune III"),
                "",
                Utils.color("&7Shoots &b3 &7arrows at once."),
                Utils.color("&7Can damage enderman."),
                "",
                Utils.color("&cDivides your &9☣ Crit Chance &cby 4!"),
                "",
                Utils.color("&6Ability: Salvation &e&lLEFT CLICK"),
                Utils.color("&7Can be casted after landing &6&l3 &7hits."),
                Utils.color("&7Shoot a beam, penetrating up to &e5"),
                Utils.color("&7enemies."),
                Utils.color("&7The beam always crits."),
                Utils.color("&8Soulflow Cost: &3&l1⸎"),
                "",
                Utils.color("&dShortbow: Instantly shoots!"),
                "",
                Utils.color("&d&l&kA&a &d&lMYTHIC DUNGEON BOW &kA")
        );

        meta.setLore(lore);
        meta.addEnchant(Enchantment.UNBREAKING, 100, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        terminatorBow.setItemMeta(meta);

        return terminatorBow;
    }
}
