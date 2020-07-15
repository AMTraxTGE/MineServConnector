package com.amtraxtge.msc.utilities.logging;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class MineLogger {
    private static String prefix = "[MineServConnector] ";

    public static void info(String msg) {
        Bukkit.getLogger().info(prefix + msg);
    }

    public static void warning(String msg) {
        Bukkit.getLogger().warning(prefix + msg);
    }

    public static void warning(String msg, Throwable e) {
        Bukkit.getLogger().log(Level.WARNING, prefix + msg, e);
    }

    public static void severe(String msg) {
        Bukkit.getLogger().severe(msg);
    }

    public static void severe(String msg, Throwable e) {
        Bukkit.getLogger().log(Level.SEVERE, msg, e);
    }

    public static void log(Level level, String msg, Throwable e) {
        Bukkit.getLogger().log(level, prefix + msg, e);
    }
}
