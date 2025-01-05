package me.mvez73.furnaceenhanced;

import me.mvez73.furnaceenhanced.events.FurnacesListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FurnaceEnhanced extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(new FurnacesListener(), this);
        int pluginId = 15824; // <-- Replace with the id of your plugin!
        new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}