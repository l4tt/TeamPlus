package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Methods.Team;
import net.stealsmp.team_plus.Team_Plus;
import net.stealsmp.team_plus.files.Playerdata;
import net.stealsmp.team_plus.files.Teamdata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeamCreate implements Listener, CommandExecutor {
    public String Error_Log  = "&x&f&b&0&0&0&0E&x&f&c&1&0&2&br&x&f&d&2&0&5&7r&x&f&e&3&0&8&2o&x&f&f&4&0&a&dr";

    Team_Plus plugin;
    public TeamCreate(Team_Plus instance) {
        plugin = instance;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args){
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Error_Log) + " | " + ChatColor.RED + "Only player's can run this command!");
            return true;
        }

        Player player = (Player) sender;


        try {
            String TeamName = args[0];
            String TeamPrefix = args[1];
            if ((TeamPrefix.length() == 4) || (TeamPrefix.length() == 5)) {
                if (player.hasPermission("teamplus.create")) {
                    if (net.stealsmp.team_plus.Methods.Player.getClan(player) == null){
                        if(Teamdata.Clan.getString("Team." + TeamName) == null){
                            net.stealsmp.team_plus.Methods.Team.createClan(player, TeamName, TeamPrefix);
                            Team.setPvp(TeamName, "True");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+ChatColor.GRAY +" ▶"+ChatColor.RESET+" Created Team");

                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "Looks like there is already a team with this name, if there is a issue please contact us");
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+ " | " + ChatColor.RED + "Looks like you are already in a Team please leave it to join another");
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + " | " + ChatColor.RED + "You don't have permissions to run this command!");
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+ChatColor.GRAY +" ▶"+ChatColor.RESET+" Prefix has to be 4 - 5 Characters long");
            }

        } catch (Exception e){
            help(player);
        }



        return true;

    }
    private void help(Player p){
        if(p.hasPermission("teamplus.create")){

            p.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");

            p.sendMessage("║ "+ChatColor.YELLOW+"/tcreate"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" <Name> <Prefix>");
        }

    }

}
