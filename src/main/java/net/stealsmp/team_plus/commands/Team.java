package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Team_Plus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Team implements CommandExecutor {
    Team_Plus plugin;
    public Team(Team_Plus instance) {
        plugin = instance;
    }
    public String Error_Log  = "&x&f&b&0&0&0&0E&x&f&c&1&0&2&br&x&f&d&2&0&5&7r&x&f&e&3&0&8&2o&x&f&f&4&0&a&dr";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Error_Log) + " | " + ChatColor.RED + "Only player's can run this command!");
            return true;
        }
        if (!sender.hasPermission("teamplus.create")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Error_Log) + " | " + ChatColor.RED + "You don't have permissions to run this command!");
            return true;
        }

        sender.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");
        sender.sendMessage("║ "+ChatColor.YELLOW+"/tstats"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" Show team stats members/owners");

        sender.sendMessage("║ "+ChatColor.YELLOW+"/tleave"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" Disband your team or leave team!");

        sender.sendMessage("║ "+ChatColor.YELLOW+"/tcreate"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" Create a team");

        sender.sendMessage("║ "+ChatColor.YELLOW+"/tcombat"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" Toggle combat (PvP) for your team");

        sender.sendMessage("║ "+ChatColor.YELLOW+"/tkick"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" Kick players from you're team");
        sender.sendMessage("║ " + "Made with love by N..#5540 for stealsmp.net");

        return true;
    }

}
