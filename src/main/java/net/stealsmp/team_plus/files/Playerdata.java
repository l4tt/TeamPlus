package net.stealsmp.team_plus.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Playerdata {
    public static File Playerfile = new File("plugins/Team_Plus", "PlayerDat.yml");
    public static FileConfiguration Player = YamlConfiguration.loadConfiguration(Playerfile);
    public static void savePlayerFile() {
        try {
            Player.save(Playerfile);
        }catch(IOException f) {
            f.printStackTrace();
        }
    }
}