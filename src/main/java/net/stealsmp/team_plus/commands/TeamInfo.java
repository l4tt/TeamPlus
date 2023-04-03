package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Methods.Stats;
import net.stealsmp.team_plus.Team_Plus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class TeamInfo implements CommandExecutor {
    Team_Plus plugin;
    public TeamInfo(Team_Plus instance) {
        plugin = instance;
    }
    public String Error_Log  = "&x&f&b&0&0&0&0E&x&f&c&1&0&2&br&x&f&d&2&0&5&7r&x&f&e&3&0&8&2o&x&f&f&4&0&a&dr";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Error_Log) + " | " + ChatColor.RED + "Only player's can run this command!");
            return true;
        }

        org.bukkit.entity.Player p = (org.bukkit.entity.Player) sender;
        if (net.stealsmp.team_plus.Methods.Player.getClan(p) != null){
            String Team = net.stealsmp.team_plus.Methods.Player.getClan(p);
            sender.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");
            sender.sendMessage("║ "+ChatColor.YELLOW+"Team: " + Team);
            sender.sendMessage("║ "+ChatColor.YELLOW+"Members: ");
            for (String uuid : net.stealsmp.team_plus.Methods.Team
                    .getMember(net.stealsmp.team_plus.Methods.Player.getClan(p))) {
                p.sendMessage("║ §7- §8" + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
            }
            sender.sendMessage("║ "+ChatColor.YELLOW+"Owner: ");
            for (String uuid : net.stealsmp.team_plus.Methods.Team
                    .getAdmins(net.stealsmp.team_plus.Methods.Player.getClan(p))) {
                p.sendMessage("║ §7- §8" + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
            }
            int kills = Stats.getkills(Team);
            int deaths = Stats.getdeaths(Team);
            sender.sendMessage("║ "+ChatColor.YELLOW+"Kills: " + kills);
            sender.sendMessage("║ "+ChatColor.YELLOW+"Deaths: " + deaths);
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "Looks like you aren't in a team, please create a team to access this command");

        }


        return true;
    }

}