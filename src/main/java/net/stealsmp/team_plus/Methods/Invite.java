package net.stealsmp.team_plus.Methods;

import net.stealsmp.team_plus.Team_Plus;
import net.stealsmp.team_plus.files.Teamdata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Invite {
    static Team_Plus plugin;
    public Invite(Team_Plus instance) {
        plugin = instance;
    }
    public static boolean PlayerJoin(OfflinePlayer p, String clan) {
        List<String> invites = Player.getInvites(p);
        if(invites.contains(clan)) {
            int players = Team.getAdmins(clan).size() + Team.getMember(clan).size();
            if(players < Team_Plus.instance.getConfig().getInt("max_player_team")) {
                if(!(Teamdata.Clan.getString("Team." + clan) == null)) {
                    Player.setClan(p, clan);
                    Player.removeInvite(p, clan);
                    Playergejoint(p, clan);
                    return true;
                }else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void Playergejoint(OfflinePlayer player, String clan) {
        List<String> list = Team.getAdmins(clan);
        for(String uuid : list) {
            OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            if(pl.isOnline()) {
                Bukkit.getPlayer(UUID.fromString(uuid)).sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Team_Plus.instance.getConfig().getString("prefix"))  + ChatColor.GOLD + player.getName() + " Has joined " + ChatColor.GOLD + clan));

            }
        }
        List<String> listm = Team.getMember(clan);
        for(String uuid : listm) {
            OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            if(pl.isOnline()) {
                Bukkit.getPlayer(UUID.fromString(uuid)).sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Team_Plus.instance.getConfig().getString("prefix")) + ChatColor.GOLD + player.getName()  + ChatColor.RESET + " has joined your team"));
            }
        }
    }
}
