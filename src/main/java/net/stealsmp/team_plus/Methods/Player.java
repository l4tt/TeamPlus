package net.stealsmp.team_plus.Methods;

import net.stealsmp.team_plus.files.Playerdata;
import net.stealsmp.team_plus.files.Teamdata;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static FileConfiguration data = Playerdata.Player;
    public static String getClan(OfflinePlayer p) {
        try {
            return data.getString("Player." + p.getUniqueId() + ".Team");
        } catch(Exception k) {
            return null;
        }
    }
    public static void setClan(OfflinePlayer p, String clan) {
        Team.addMember(p, clan);
        data.set("Player." + p.getUniqueId() + ".Team", clan);
        Playerdata.savePlayerFile();
    }

    public static boolean isadmin(OfflinePlayer p, String clan) {
        List<String> admins = Teamdata.Clan.getStringList("Team." + clan + ".admins");
        if(admins.contains(p.getUniqueId().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static void invitePlayer(OfflinePlayer p, String clan) {
        try {
            List<String> invite = data.getStringList("Player." + p.getUniqueId() + ".invite");
            if(!invite.contains(clan)) {
                invite.add(clan);
            }
            data.set("Player." + p.getUniqueId() + ".invite", invite);
            Playerdata.savePlayerFile();
        } catch(Exception k) {
            List<String> invite = new ArrayList<String>();
            invite.add(clan);
            data.set("Player." + p.getUniqueId() + ".invite", invite);
            Playerdata.savePlayerFile();
        }
    }

    public static void removeInvite(OfflinePlayer p, String clan) {
        try {
            List<String> invite = data.getStringList("Player." + p.getUniqueId() + ".invite");
            invite.remove(clan);
            data.set("Player." + p.getUniqueId() + ".invite", invite);
            Playerdata.savePlayerFile();
        } catch(Exception f) {
            f.printStackTrace();
        }
    }

    public static List<String> getInvites(OfflinePlayer p) {
        return data.getStringList("Player." + p.getUniqueId() + ".invite");
    }

    public static void leaveClan(OfflinePlayer p, String clan) {
        data.set("Player." + p.getUniqueId() + ".Team", null);
        Playerdata.savePlayerFile();
        if(isadmin(p, clan)) {
            Team.removeAdmin(p, clan);
        } else {
            Team.removeMember(p, clan);
        }
    }
}
