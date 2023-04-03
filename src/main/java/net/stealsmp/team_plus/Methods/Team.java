package net.stealsmp.team_plus.Methods;

import net.stealsmp.team_plus.Team_Plus;
import net.stealsmp.team_plus.files.Teamdata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {
    private static FileConfiguration data = Teamdata.Clan;
    public static void createClan(OfflinePlayer p, String clan, String kurz) {
        data.set("Team." + clan + ".prefix", kurz);
        Player.setClan(p, clan);
        Team.promotePlayer(p, clan);
        Teamdata.saveClanFile();
    }

    public static void setPvp(String clan, String mode){
        data.set("Team." + clan + ".pvp", mode);
        Teamdata.saveClanFile();
    }
    public static void addMember(OfflinePlayer p, String clan) {
        try {
            List<String> member = data.getStringList("Team." + clan + ".members");
            member.add(p.getUniqueId().toString());
            data.set("Team." + clan + ".members", member);
            Teamdata.saveClanFile();
        } catch(Exception k) {
            List<String> member = new ArrayList<>();
            member.add(p.getUniqueId().toString());
            data.set("Team." + clan + ".members", member);
            Teamdata.saveClanFile();
        }
    }
    public static void removeMember(OfflinePlayer p, String clan) {
        try {
            List<String> member = data.getStringList("Team." + clan + ".members");
            member.remove(p.getUniqueId().toString());
            data.set("Team." + clan + ".members", member);
            Teamdata.saveClanFile();
        } catch(Exception k) {
            k.printStackTrace();
        }
    }
    public static void addAdmin(OfflinePlayer p, String clan) {
        try {
            List<String> admins = data.getStringList("Team." + clan + ".admins");
            admins.add(p.getUniqueId().toString());
            data.set("Team." + clan + ".admins", admins);
            Teamdata.saveClanFile();
        } catch(Exception k) {
            List<String> admins = new ArrayList<String>();
            admins.add(p.getUniqueId().toString());
            data.set("Team." + clan + ".admins", admins);
            Teamdata.saveClanFile();
        }
    }
    public static List<String> getMember(String clan) {
        try {
            List<String> member = data.getStringList("Team." + clan + ".members");
            return member;
        }
        catch(Exception k) {
            return new ArrayList<String>();
        }
    }
    public static List<String> getAdmins(String clan) {
        try {
            List<String> admins = data.getStringList("Team." + clan + ".admins");
            return admins;
        }
        catch(Exception k) {
            return new ArrayList<String>();
        }
    }
    public static void promotePlayer(OfflinePlayer p, String clan) {
        removeMember(p, clan);
        addAdmin(p, clan);
    }

    public static void deleteClan(String clan) {
        for(String uuid : getMember(clan)) {
            Player.leaveClan(Bukkit.getOfflinePlayer(UUID.fromString(uuid)), clan);
            if(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).isOnline()) {
                Bukkit.getPlayer(UUID.fromString(uuid)).sendMessage(ChatColor.translateAlternateColorCodes('&', Team_Plus.instance.getConfig().getString("prefix")) + "Team Owner has deleted your team");
            }
        }
        for(String uuid : getAdmins(clan)) {
            Player.leaveClan(Bukkit.getOfflinePlayer(UUID.fromString(uuid)), clan);
            if(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).isOnline()) {
                Bukkit.getPlayer(UUID.fromString(uuid)).sendMessage(ChatColor.translateAlternateColorCodes('&', Team_Plus.instance.getConfig().getString("prefix")) + "Team Owner has deleted your team");
            }
        }
        data.set("Team." + clan, null);
        data.set("Team." + clan, " ");
        Teamdata.saveClanFile();
    }
    public static void removeAdmin(OfflinePlayer p, String clan) {
        try {
            List<String> admin = data.getStringList("Team." + clan + ".admins");
            admin.remove(p.getUniqueId().toString());
            data.set("Team." + clan + ".admins", admin);
            Teamdata.saveClanFile();
        } catch(Exception k) {
            k.printStackTrace();
        }
    }
}
