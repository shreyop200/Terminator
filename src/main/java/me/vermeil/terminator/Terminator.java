/**
 * This class represents the main plugin class for Terminator.
 * It registers event listeners and command executors upon plugin enable.
 */
package me.vermeil.terminator;

import me.vermeil.terminator.commands.TerminatorCommand;
import me.vermeil.terminator.events.TerminatorEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Terminator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register event listener
        getServer().getPluginManager().registerEvents(new TerminatorEventListener(this), this);

        // Register command executor
        Objects.requireNonNull(getCommand("giveterminator")).setExecutor(new TerminatorCommand(this));
    }
}
