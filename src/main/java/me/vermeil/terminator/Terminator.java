package me.vermeil.terminator;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.EnumSet;
import java.util.Set;

public class Terminator extends JavaPlugin implements Listener {

    private static final Set<Action> CLICK_ACTIONS = EnumSet.of(
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK,
            Action.LEFT_CLICK_AIR,
            Action.LEFT_CLICK_BLOCK
    );

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() != Material.BOW) {
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
    }
}
