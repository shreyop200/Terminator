package dev.mitra88.terminator.commands;

import dev.mitra88.terminator.builder.TerminatorBuilder;
import dev.mitra88.terminator.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("ALL")
public class TerminatorCommand implements CommandExecutor {

    public TerminatorCommand() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        ItemStack terminatorBow = TerminatorBuilder.giveTerminator();
        player.getInventory().addItem(terminatorBow);
        player.sendMessage(ColorUtils.color("&aYou have received the Terminator."));

        return true;
    }
}
