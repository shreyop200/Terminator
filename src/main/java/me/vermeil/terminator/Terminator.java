package me.vermeil.terminator;

import me.vermeil.terminator.builder.TerminatorBuilder;
import me.vermeil.terminator.commands.TerminatorCommand;
import me.vermeil.terminator.events.MyEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Terminator extends JavaPlugin implements CommandExecutor {

    private static final Set<Action> CLICK_ACTIONS = EnumSet.of(
            Action.RIGHT_CLICK_AIR,
            Action.RIGHT_CLICK_BLOCK,
            Action.LEFT_CLICK_AIR,
            Action.LEFT_CLICK_BLOCK
    );

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MyEvent(this), this);
        Objects.requireNonNull(getCommand("giveterminator")).setExecutor(this);
        Objects.requireNonNull(getCommand("terminator")).setExecutor(new TerminatorCommand(this));
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
        ItemStack terminatorBow = TerminatorBuilder.giveTerminator();
        player.getInventory().addItem(terminatorBow);
        player.sendMessage(ChatColor.GREEN + "You have received the Terminator");

        return true;
    }
}
