package com.amtraxtge.msc.utilities.configuration;

import com.amtraxtge.msc.MineServConnector;
import com.amtraxtge.msc.utilities.configuration.sections.MineServSection;
import com.amtraxtge.msc.utilities.configuration.sections.MineSocketSection;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.net.InetSocketAddress;
import java.util.UUID;

public class MineConfig {
    private static MineConfig instance;
    private MineServConnector plugin = (MineServConnector) Bukkit.getPluginManager().getPlugin("MineServConnector");
    private FileConfiguration config = plugin.getConfig();

    private MineServSection mineServSection;
    private MineSocketSection mineSocketSection;

    private MineConfig() {
        load();
    }

    public static MineConfig get() {
        if (instance == null) instance = new MineConfig();
        return instance;
    }

    private void load() {
        if (!config.isSet("MineSocket.uuid")) {
            MineSocketSection.create(config.createSection("MineSocket"));

            config.options().copyDefaults(true);
            plugin.saveConfig();
        }

        mineSocketSection = MineSocketSection.load(config.getConfigurationSection("MineSocket"));
    }

    public UUID serverId() { return mineSocketSection.uuid; }
    public Boolean sslEnabled() {
        return mineSocketSection.useSSL;
    }
    public InetSocketAddress serverAddress() {
        return new InetSocketAddress(mineSocketSection.bind, mineSocketSection.port);
    }
}
