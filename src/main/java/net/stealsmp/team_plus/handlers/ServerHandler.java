package net.stealsmp.team_plus.handlers;

import net.stealsmp.team_plus.Methods.Stats;
import net.stealsmp.team_plus.Methods.Team;
import net.stealsmp.team_plus.Team_Plus;
import net.stealsmp.team_plus.files.Teamdata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ServerHandler implements Listener {
    Team_Plus plugin;
    public ServerHandler(Team_Plus instance) {
        plugin = instance;
    }

    private static final HashMap<UUID, Scoreboard> scoreboards = new HashMap<UUID, Scoreboard>();
    private static Objective objective;

    private static final FileConfiguration data = Teamdata.Clan;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (Team_Plus.s.getObjective("health") != null){
            Team_Plus.s.getObjective("health").unregister();
        }


        //registerTeam(player);

        // Currently broken !!

        //player.sendMessage(Objects.requireNonNull(net.stealsmp.team_plus.Methods.Player.getClan(player)));
        if (net.stealsmp.team_plus.Methods.Player.getClan(player) == null){
            String Team = net.stealsmp.team_plus.Methods.Player.getClan(player);
            String Username = event.getPlayer().getName();

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) +ChatColor.GRAY +" ▶" + ChatColor.RESET + " do " + ChatColor.GOLD + "/team " + ChatColor.RESET + "get started");


        } else {
            msgisonline(player, net.stealsmp.team_plus.Methods.Player.getClan(player));
        }

        List<String> invites = net.stealsmp.team_plus.Methods.Player.getInvites(player);
        if(!invites.isEmpty()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) +ChatColor.GRAY +" ▶" + " You have received team invites!");
            for(String clan : invites) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")))+" §e-§c§l " + clan);
            }
        }

    }

    public void registerTeam(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        objective = board.registerNewObjective("health", "health");
        String TT = net.stealsmp.team_plus.Methods.Player.getClan(player);

        //org.bukkit.scoreboard.Team t = Team_Plus.s.registerNewTeam("team");
        //t.setPrefix( "[" + ChatColor.GOLD + TT.toUpperCase()  + ChatColor.RESET + "] ");
        if (TT == null){

            objective.setDisplayName(ChatColor.RED+"❤" + ChatColor.RESET  + "[" + ChatColor.RED + "No Team"  + ChatColor.RESET + "]");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            scoreboards.put(player.getUniqueId(), board);
            player.setScoreboard(scoreboards.get(player.getUniqueId()));

        } else {
            objective.setDisplayName(ChatColor.RED+"❤" + ChatColor.RESET + "[" + ChatColor.GOLD + TT + ChatColor.RESET + "]");

            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
            scoreboards.put(player.getUniqueId(), board);
            player.setScoreboard(scoreboards.get(player.getUniqueId()));
        }


    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {



        String Team = net.stealsmp.team_plus.Methods.Player.getClan(Bukkit.getPlayer(event.getDamager().getName()));

        if(Objects.equals(data.getString("Team." + Team + ".pvp"), "True")){
            if (Team != null){
                if(Objects.equals(net.stealsmp.team_plus.Methods.Player.getClan(Bukkit.getPlayer(event.getEntity().getName())), Team)){
                    event.getDamager().sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))) + ChatColor.GRAY +"You Can't Damage Player's in your team!");
                    event.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void onPlayerDeaths(PlayerDeathEvent e) {
        Player pl = e.getEntity();
        Stats.addDeath(pl);
        try {
            Player killer = pl.getKiller();
            Stats.addKill(killer);
        } catch(Exception k) {
            String none = "";
        }
    }


    public void msgisonline(OfflinePlayer p, String clan) {
        List<String> list = Team.getAdmins(clan);
        for(String uuid : list) {
            OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            if(pl.isOnline()) {
                Bukkit.getPlayer(UUID.fromString(uuid)).sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix"))  + ChatColor.GOLD + p.getName() + ChatColor.RESET +" from you team has joined "));

            }
        }
        List<String> listm = Team.getMember(clan);
        for(String uuid : listm) {
            OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            if(pl.isOnline()) {
                Bukkit.getPlayer(UUID.fromString(uuid)).sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")) + ChatColor.GOLD + p.getName()  + ChatColor.RESET + " from your team has joined"));
            }
        }
    }
}
