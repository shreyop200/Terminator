package me.vermeil.terminator;

import me.vermeil.terminator.commands.TerminatorCommand;
import me.vermeil.terminator.events.TerminatorEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Terminator extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TerminatorEventListener(this), this);
        Objects.requireNonNull(getCommand("giveterminator")).setExecutor(new TerminatorCommand(this));
    }
}
