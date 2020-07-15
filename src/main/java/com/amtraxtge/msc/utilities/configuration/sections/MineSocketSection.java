package com.amtraxtge.msc.utilities.configuration.sections;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;
import java.util.UUID;

public class MineSocketSection {
    public UUID uuid;
    public Boolean useSSL;
    public String bind;
    public Integer port;

    private MineSocketSection() {
        this.uuid = UUID.randomUUID();
        this.useSSL = false;
        this.bind = "0.0.0.0";
        this.port = 8080;
    }

    private MineSocketSection(UUID uuid, Boolean useSSL, String bind, Integer port) {
        this.uuid = uuid;
        this.useSSL = useSSL;
        this.bind = bind;
        this.port = port;
    }

    public static void create(ConfigurationSection section) {
        MineSocketSection mss = new MineSocketSection();
        section.addDefault("uuid", mss.uuid.toString());
        section.addDefault("useSSL", mss.useSSL);
        section.addDefault("bind", mss.bind);
        section.addDefault("port", mss.port);
    }

    public static MineSocketSection load(ConfigurationSection section) {
        return new MineSocketSection(
                UUID.fromString(Objects.requireNonNull(section.getString("uuid"))),
                section.getBoolean("useSSL"),
                section.getString("bind"),
                section.getInt("port")
        );
    }
}
