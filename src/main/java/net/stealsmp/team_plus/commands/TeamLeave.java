package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Methods.Team;
import net.stealsmp.team_plus.Team_Plus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeamLeave implements CommandExecutor {
    Team_Plus plugin;

    public TeamLeave(Team_Plus instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(net.stealsmp.team_plus.Methods.Player.getClan(p) != null){
            String Team = net.stealsmp.team_plus.Methods.Player.getClan(p);
            if(net.stealsmp.team_plus.Methods.Player.isadmin(p, Team)){
                net.stealsmp.team_plus.Methods.Team.deleteClan(Team);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) +ChatColor.GRAY +" ▶" + " Since you left your team as a owner of "+ ChatColor.GOLD + Team + ChatColor.RESET + " we have deleted your team");

            }
            net.stealsmp.team_plus.Methods.Player.leaveClan(p, Team);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) +ChatColor.GRAY +" ▶" + " You Have Left " + ChatColor.GOLD + Team);

        }

        return true;
    }
}