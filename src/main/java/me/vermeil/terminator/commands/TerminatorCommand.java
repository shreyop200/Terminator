package me.vermeil.terminator.commands;

import me.vermeil.terminator.Terminator;
import me.vermeil.terminator.builder.TerminatorBuilder;
import me.vermeil.terminator.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * A command executor that handles the "/terminator getitem" command.
 * <p></p>
 * This command can only be executed by players, and only by operators (OP players).
 * When executed, it gives the player a Terminator bow.
 */
public class TerminatorCommand implements CommandExecutor {
    /**
     * The Terminator plugin instance.
     */
    private final Terminator plugin;

    /**
     * Constructs a new MyCommand instance with the given plugin.
     *
     * @param plugin the Terminator plugin instance
     */

    public TerminatorCommand(Terminator plugin) {
        this.plugin = plugin;
    }

    /**
     * Executes the command.
     *
     * @param sender  the command sender (must be a player)
     * @param command the command being executed
     * @param s       the command label (e.g. "terminator")
     * @param args    the command arguments (must be "getitem")
     * @return true if the command was executed successfully, false otherwise
     */

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            // log message to the console
            plugin.getLogger().info("Only players can execute this command");
            return true;
        }
        if (args.length == 1
                && command.getName().equalsIgnoreCase("terminator")
                && args[0].equalsIgnoreCase("getitem")) {
            // if the command is /terminator getitem
            Player player = (Player) sender;

            // Check if the player is an operator (OP player)
            //why not all players can get the terminator bow?
            if (!player.isOp()) {
                return true;
            }
            // Give the player a Terminator bow
            ItemStack terminatorBow = TerminatorBuilder.giveTerminator();
            player.getInventory().addItem(terminatorBow);

            // sends a player a message with color codes
            player.sendMessage(utils.color("&2You have received the Terminator"));
            return true;
        }
        return true;
    }
}
