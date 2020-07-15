package com.amtraxtge.msc.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MineServCommand implements CommandExecutor {
    private String version;
    private Boolean enabled;

    public MineServCommand(String version, Boolean enabled) {
        this.version = version;
        this.enabled = enabled;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder msg = new StringBuilder();

        msg.append("&6--------- &cMineServConnector &6-----------\n");
        msg.append("&3Version&9: &d" + version + "\n");
        msg.append("&3Author&9:  &dAMTraxTGE\n");
        msg.append("&3Issues&9:  &dhttps://bit.ly/msissues\n");
        msg.append("&3Discord&9: &dhttps://discord.gg/eGUbDNY\n");
        msg.append("&3Status&9:  &d[&a" + StatusMessage() + "&d]\n");
        msg.append("&6-------------------------------------");

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.toString()));
        return true;
    }

    private String StatusMessage() {
        return enabled ? "&aOnline" : "&cOffline";
    }
}
