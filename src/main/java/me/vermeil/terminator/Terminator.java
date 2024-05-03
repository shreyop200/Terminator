package me.vermeil.terminator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class Terminator extends JavaPlugin implements Listener, CommandExecutor {

    private static final Set<Action> CLICK_ACTIONS = EnumSet.of(
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK,
            Action.LEFT_CLICK_AIR,
            Action.LEFT_CLICK_BLOCK
    );

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("giveterminator")).setExecutor(this);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            return true;
        }

        ItemStack terminatorBow = giveTerminator();
        player.getInventory().addItem(terminatorBow);
        player.sendMessage(ChatColor.GREEN + "You have received the Terminator");

        return true;
    }

    private ItemStack giveTerminator() {
        ItemStack terminatorBow = new ItemStack(Material.BOW);
        ItemMeta meta = terminatorBow.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(ChatColor.LIGHT_PURPLE + "Hasty Terminator " + ChatColor.GOLD + "✪✪✪✪✪" + ChatColor.RED + "➎");

        List<String> lore = Arrays.asList(
                ChatColor.GRAY + "Gear Score: " + ChatColor.LIGHT_PURPLE + "1014 " + ChatColor.DARK_GRAY + "(3467)",
                ChatColor.GRAY + "Damage: " + ChatColor.RED + "371 " + ChatColor.YELLOW + "(+30) " + ChatColor.DARK_GRAY + "(+1,598)",
                ChatColor.GRAY + "Strength: " + ChatColor.RED + "110 " + ChatColor.YELLOW + "(+30) " + ChatColor.GOLD + "[+5] "  + ChatColor.BLUE + "(+20) " + ChatColor.DARK_GRAY + "(+493.5)",
                ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+65% " + ChatColor.BLUE + "(+60%) " + ChatColor.DARK_GRAY + "(+100,75%)",
                ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+280% " + ChatColor.DARK_GRAY + "(+1,198,5%)",
                ChatColor.GRAY + "Bonus Attack Speed: " + ChatColor.RED + "+44% " + ChatColor.DARK_GRAY + "(+62%)",
                ChatColor.GRAY + "Shot Cooldown: " + ChatColor.GREEN + "0.5s",
                "",
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Soul Eater V " + ChatColor.BLUE + "Chance V, Cubism V",
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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || !item.hasItemMeta()) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (!Objects.requireNonNull(meta).hasDisplayName() || !meta.getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Hasty Terminator " + ChatColor.GOLD + "✪✪✪✪✪" + ChatColor.RED + "➎")) {
            return;
        }

        if (!CLICK_ACTIONS.contains(event.getAction())) {
            return;
        }

        event.setCancelled(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                Vector forward = player.getLocation().getDirection();
                Vector right = forward.clone().rotateAroundY(Math.toRadians(15));
                Vector left = forward.clone().rotateAroundY(Math.toRadians(-15));

                shootArrow(player, forward);
                shootArrow(player, right);
                shootArrow(player, left);
            }
        }.runTask(this);
    }

    private void shootArrow(Player player, Vector direction) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setCritical(true);
        arrow.setVelocity(direction.multiply(4));
        arrow.setDamage(371);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // Credit to (https://www.spigotmc.org/threads/forcing-enderman-to-take-damage-from-projectiles.511665/#post-4336602)

        if (!(event.getEntity() instanceof Arrow)) {
            return;
        }

        Arrow arrow = (Arrow) event.getEntity();
        Player shooter = null;
        if (arrow.getShooter() instanceof Player) {
            shooter = (Player) arrow.getShooter();
        }

        if (shooter == null) {
            return;
        }

        for (Entity entity : arrow.getNearbyEntities(3, 3, 3)) {
            if (entity instanceof Enderman) {
                Enderman enderman = (Enderman) entity;
                enderman.damage(371, shooter);
                arrow.remove();
            }
        }
    }
}
