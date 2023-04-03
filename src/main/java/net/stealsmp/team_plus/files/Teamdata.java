package net.stealsmp.team_plus.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Teamdata {
    public static File Clanfile = new File("plugins/Team_Plus", "Teamdata.yml");
    public static FileConfiguration Clan = YamlConfiguration.loadConfiguration(Clanfile);
    public static void saveClanFile() {
        try {
            Clan.save(Clanfile);
        }catch(IOException f) {
            f.printStackTrace();
        }
    }
}