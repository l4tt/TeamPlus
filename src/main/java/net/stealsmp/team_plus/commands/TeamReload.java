package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Team_Plus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeamReload implements CommandExecutor {
    Team_Plus plugin;

    public TeamReload(Team_Plus instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("teamplus.reload")){
            if (save()){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) +ChatColor.GRAY +" ▶" + " Reloaded Config");
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) +ChatColor.GRAY +" ▶" + " Error in the config");
            }
        }

        return true;
    }
    public boolean save(){
        try {
            Team_Plus.instance.getConfig();

            Team_Plus.instance.saveConfig();

            Team_Plus.instance.reloadConfig();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}