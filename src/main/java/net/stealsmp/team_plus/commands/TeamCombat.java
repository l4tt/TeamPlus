package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Methods.Player;
import net.stealsmp.team_plus.Methods.Team;
import net.stealsmp.team_plus.Team_Plus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class TeamCombat implements CommandExecutor {
    Team_Plus plugin;

    public TeamCombat(Team_Plus instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        org.bukkit.entity.Player p = (org.bukkit.entity.Player) sender;
        try {

            String mode = args[0];
            String TEAM = Player.getClan(p);
            if (TEAM != null){
                if (Player.isadmin(p, TEAM)){
                    if (Objects.equals(mode, "true")){
                        Team.setPvp(TEAM, "False");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "You have " + ChatColor.GREEN +"Enabled" + ChatColor.RESET+ " PVP for your team");

                    } else if (mode.equals("false")) {
                        Team.setPvp(TEAM, "True");
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "You have " + ChatColor.RED +"Disabled" + ChatColor.RESET+ " PVP for your team");

                    } else {
                        p.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");

                        p.sendMessage("║ "+ChatColor.YELLOW+"/tcombat"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" true or false ");
                    }
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "You don't have permissions to run this command!");
                }

            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "Please Create a team to use this command!");

            }

        } catch (Exception e){
            p.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");

            p.sendMessage("║ "+ChatColor.YELLOW+"/tcombat"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" true or false ");
        }
        return true;
    }
}