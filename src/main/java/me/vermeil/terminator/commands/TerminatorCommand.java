/**
 * This class implements the command executor for the Terminator command.
 * It allows players to receive the Terminator.
 */
package me.vermeil.terminator.commands;

import me.vermeil.terminator.Terminator;
import me.vermeil.terminator.builder.TerminatorBuilder;
import me.vermeil.terminator.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TerminatorCommand implements CommandExecutor {
    private final Terminator plugin;

    /**
     * Constructor for the TerminatorCommand class.
     *
     * @param plugin The main plugin instance.
     */
    public TerminatorCommand(Terminator plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the Terminator command.
     *
     * @param sender  The command sender.
     * @param command The command executed.
     * @param label   The command label.
     * @param args    The command arguments.
     * @return true if the command was executed successfully, otherwise false.
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            plugin.getLogger().info("Only players can execute this command");
            return true;
        }

        // Give the player the Terminator Bow item
        ItemStack terminatorBow = TerminatorBuilder.giveTerminator();
        player.getInventory().addItem(terminatorBow);
        player.sendMessage(Utils.color("&2You have received the Terminator."));

        return true;
    }
}
