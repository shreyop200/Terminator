package me.vermeil.terminator.commands;

import me.vermeil.terminator.Terminator;
import me.vermeil.terminator.builder.TerminatorBuilder;
import me.vermeil.terminator.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TerminatorCommand implements CommandExecutor {
    private final Terminator plugin;

    public TerminatorCommand(Terminator plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLogger().info("Only players can execute this command");
            return true;
        }
        Player player = (Player) sender;

        ItemStack terminatorBow = TerminatorBuilder.giveTerminator();
        player.getInventory().addItem(terminatorBow);
        player.sendMessage(ColorUtils.color("&aYou have received the Terminator."));

        return true;
    }
}
