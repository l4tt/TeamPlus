package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Team_Plus;
import net.stealsmp.team_plus.files.Teamdata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeamInvite implements CommandExecutor {
    Team_Plus plugin;
    public TeamInvite(Team_Plus instance) {
        plugin = instance;
    }
    public String Error_Log  = "&x&f&b&0&0&0&0E&x&f&c&1&0&2&br&x&f&d&2&0&5&7r&x&f&e&3&0&8&2o&x&f&f&4&0&a&dr";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Error_Log) + " | " + ChatColor.RED + "Only player's can run this command!");
            return true;
        }
        Player p = (Player) sender;
        try {

            String player = args[0];

            if (!(net.stealsmp.team_plus.Methods.Player.getClan(p) == null)){
                if (net.stealsmp.team_plus.Methods.Player.isadmin(p, net.stealsmp.team_plus.Methods.Player.getClan(p))){
                    if (Objects.equals(player, p.getName())){
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "Can't Invite your self to the same team");

                        return true;
                    }
                    net.stealsmp.team_plus.Methods.Player.invitePlayer(Bukkit.getOfflinePlayer(player), net.stealsmp.team_plus.Methods.Player.getClan(p));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " You have invited " + ChatColor.GOLD + player);

                    if (Bukkit.getOfflinePlayer(player).isOnline()) {
                        Bukkit.getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "You have received an team invite from " + ChatColor.GOLD + net.stealsmp.team_plus.Methods.Player.getClan(p));
                        Bukkit.getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "to join do "+ ChatColor.GOLD +" /tjoin " + net.stealsmp.team_plus.Methods.Player.getClan(p));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "Only the Team Leader can invite members");
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "Looks like you are already in a Team please leave it to join another");

            }
        } catch (Exception e){
            help(p);
        }



        return true;

    }
    private void help(Player p){
        if(p.hasPermission("teamplus.invite")){

            p.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");

            p.sendMessage("║ "+ChatColor.YELLOW+"/tinvite"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" <Player_Name>");
        }

    }
}
