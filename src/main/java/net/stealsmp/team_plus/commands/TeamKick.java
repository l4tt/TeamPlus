package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Team_Plus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeamKick implements CommandExecutor {
    Team_Plus plugin;

    public TeamKick(Team_Plus instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player p = (Player) sender;

        try {
            String player = args[0];
            if (net.stealsmp.team_plus.Methods.Player.getClan(p) != null) {
                String Team = net.stealsmp.team_plus.Methods.Player.getClan(p);
                if (Objects.equals(player, p.getName())){
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "Can't kick yourself!");
                    return true;
                }
                if (net.stealsmp.team_plus.Methods.Player.isadmin(p, Team)) {
                    net.stealsmp.team_plus.Methods.Player.leaveClan(Bukkit.getOfflinePlayer(player), Team);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "Invited " + ChatColor.GOLD + player);

                    if (Bukkit.getOfflinePlayer(player).isOnline()) {
                        Bukkit.getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "You have been " + ChatColor.RED + "kicked" + ChatColor.RESET + " from " + ChatColor.GOLD + net.stealsmp.team_plus.Methods.Player.getClan(p));
                    }
                }

            }
        } catch (Exception e){
            p.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");

            p.sendMessage("║ "+ChatColor.YELLOW+"/tkick"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" <Player_name>");
        }

        return true;
    }
}