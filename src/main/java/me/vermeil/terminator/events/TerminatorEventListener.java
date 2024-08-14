package me.vermeil.terminator.events;

import me.vermeil.terminator.Terminator;
import me.vermeil.terminator.utils.ColorUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.EnumSet;
import java.util.Set;

public class TerminatorEventListener implements Listener {
    private static final Set<Action> CLICK_ACTIONS = EnumSet.of(
            Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK,
            Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK
    );

    private final Terminator plugin;
    private static final String TARGET_DISPLAY_NAME = ColorUtils.color("&dHasty Terminator &6✪✪✪✪&c➎");

    public TerminatorEventListener(Terminator plugin) {
        this.plugin = plugin;
    }

    private void shootArrow(Player player, Vector direction) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setCritical(true);
        arrow.setVelocity(direction.multiply(4));
        arrow.setDamage(371);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !TARGET_DISPLAY_NAME.equals(meta.getDisplayName())) return;

        if (CLICK_ACTIONS.contains(event.getAction())) {
            event.setCancelled(true);
            Vector forward = event.getPlayer().getLocation().getDirection();
            new BukkitRunnable() {
                @Override
                public void run() {
                    shootArrow(event.getPlayer(), forward);
                    shootArrow(event.getPlayer(), forward.clone().rotateAroundY(Math.toRadians(15)));
                    shootArrow(event.getPlayer(), forward.clone().rotateAroundY(Math.toRadians(-15)));
                }
            }.runTask(plugin);
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow arrow && arrow.getShooter() instanceof Player shooter) {
            for (Entity entity : arrow.getNearbyEntities(3, 3, 3)) {
                if (entity instanceof Enderman enderman) {
                    enderman.damage(371, shooter);
                    arrow.remove();
                    break;
                }
            }
        }
    }
}
