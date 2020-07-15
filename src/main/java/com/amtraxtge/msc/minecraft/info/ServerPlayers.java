package com.amtraxtge.msc.minecraft.info;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServerPlayers {

    public static Collection<? extends Player> get() {
        return Bukkit.getOnlinePlayers();
    }

    public static List<String> names() {
        List<String> players = new ArrayList<String>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            players.add(player.getName());
        }
        return players;
    }
}
