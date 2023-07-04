package me.mvez73.furnaceenhanced;

import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import com.jeff_media.updatechecker.UserAgentBuilder;
import me.mvez73.furnaceenhanced.events.FurnacesListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FurnaceEnhanced extends JavaPlugin {
    public static FurnaceEnhanced plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new FurnacesListener(), this);
        int pluginId = 15824; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        new UpdateChecker(this, UpdateCheckSource.SPIGOT, "103489") // You can also use Spiget instead of Spigot - Spiget's API is usually much faster up to date.
                .setDownloadLink("https://www.spigotmc.org/resources/furnaceenhanced-1-17-1-1-20.103489/") // You can either use a custom URL or the Spigot Resource ID
                .setDonationLink("https://paypal.me/mvez73")
                .setNotifyOpsOnJoin(true) // Notify OPs on Join when a new version is found (default)
                .setUserAgent(new UserAgentBuilder().addPluginNameAndVersion()).checkEveryXHours(12) // Check every 12 hours
                .checkNow(); // And check right now

        getLogger().info("Successfully enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disabled");
    }
}
