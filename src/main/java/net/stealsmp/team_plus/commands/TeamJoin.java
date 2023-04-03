package net.stealsmp.team_plus.commands;

import net.stealsmp.team_plus.Methods.Invite;
import net.stealsmp.team_plus.Team_Plus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeamJoin implements CommandExecutor {
    Team_Plus plugin;
    public TeamJoin(Team_Plus instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        Player p = (Player) sender;
        try {
            String invite = args[0];
            if (net.stealsmp.team_plus.Methods.Player.getClan(p) != null){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + "Looks like you are already in a team to join another do " + ChatColor.GOLD + "/tleave");
                return true;
            }
            if (Invite.PlayerJoin(p, invite)){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+ChatColor.GRAY +" ▶"+ChatColor.RESET+" You have joined " + ChatColor.GOLD + invite);
            }
        } catch (Exception e){
            e.printStackTrace();
            help(p);
        }



        return true;
    }
    private void help(Player p){
        if(p.hasPermission("teamplus.join")){

            p.sendMessage("╔════"+ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+"═════");

            p.sendMessage("║ "+ChatColor.YELLOW+"/tjoin"+ChatColor.GRAY +" ▶"+ChatColor.RESET+" <Team_Name>");
        }

    }
}