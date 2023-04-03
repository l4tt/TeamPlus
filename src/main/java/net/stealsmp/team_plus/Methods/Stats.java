package net.stealsmp.team_plus.Methods;

import net.stealsmp.team_plus.files.Teamdata;
import org.bukkit.OfflinePlayer;

public class Stats {
    public static void addKill(OfflinePlayer p) {
        String clan = Player.getClan(p);
        try{
            int newkills = Teamdata.Clan.getInt("Team." + clan + ".kills")+1;
            Teamdata.Clan.set("Team." + clan + ".kills", newkills);
        }
        catch(Exception k) {
            int newkills = 1;
            Teamdata.Clan.set("Team." + clan + ".kills", newkills);
        }
        Teamdata.saveClanFile();
    }
    public static void addDeath(OfflinePlayer p) {
        String clan = Player.getClan(p);
        try{
            int newdeaths = Teamdata.Clan.getInt("Team." + clan + ".deaths")+1;
            Teamdata.Clan.set("Team." + clan + ".deaths", newdeaths);
        }
        catch(Exception k) {
            int newdeaths = 1;
            Teamdata.Clan.set("Team." + clan + ".kills", newdeaths);
        }
        Teamdata.saveClanFile();
    }
    public static int getkills(String clan) {
        try{
            return Teamdata.Clan.getInt("Team." + clan + ".kills");
        }
        catch(Exception k) {
            return 0;
        }
    }
    public static int getdeaths(String clan) {
        try{
            return Teamdata.Clan.getInt("Team." + clan + ".deaths");
        }
        catch(Exception k) {
            return 0;
        }
    }
}
