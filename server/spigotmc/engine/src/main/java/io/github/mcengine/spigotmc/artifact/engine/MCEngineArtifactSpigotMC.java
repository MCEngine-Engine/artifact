package io.github.mcengine.spigotmc.artifact.engine;

import io.github.mcengine.api.core.MCEngineCoreApi;
import io.github.mcengine.api.core.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class MCEngineArtifactSpigotMC extends JavaPlugin {

    /**
     * Called when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        new Metrics(this, 22576);
        saveDefaultConfig(); // Save config.yml if it doesn't exist

        boolean enabled = getConfig().getBoolean("enable", false);
        if (!enabled) {
            getLogger().warning("Plugin is disabled in config.yml (enable: false). Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Load extensions
        MCEngineCoreApi.loadExtensions(
            this,
            "io.github.mcengine.api.artifact.addon.IMCEngineArtifactAddOn",
            "addons",
            "AddOn"
            );
        MCEngineCoreApi.loadExtensions(
            this,
            "io.github.mcengine.api.artifact.dlc.IMCEngineArtifactDLC",
            "dlcs",
            "DLC"
            );

        MCEngineCoreApi.checkUpdate(this, getLogger(), "github", "MCEngine-Engine", "artifact", getConfig().getString("github.token", "null"));
    }

    /**
     * Called when the plugin is disabled.
     */
    @Override
    public void onDisable() {}
}
