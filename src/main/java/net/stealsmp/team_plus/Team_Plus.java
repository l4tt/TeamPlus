package net.stealsmp.team_plus;

import net.stealsmp.team_plus.commands.*;
import net.stealsmp.team_plus.files.Playerdata;
import net.stealsmp.team_plus.files.Teamdata;
import net.stealsmp.team_plus.handlers.ServerHandler;
import net.stealsmp.team_plus.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public final class Team_Plus extends JavaPlugin {
    public static Plugin instance;
    public static Scoreboard s;
    @Override
    public void onEnable() {

        getLogger().info(ChatColor.YELLOW + "TeamPlus has been loaded");
        Teamdata.saveClanFile();
        Playerdata.savePlayerFile();

        s = Bukkit.getScoreboardManager().getMainScoreboard();


        instance = this;

        final FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        ConfigUtil con = new ConfigUtil(this, "config.yml");

        Objects.requireNonNull(getCommand("team")).setExecutor(new Team(this));
        Objects.requireNonNull(getCommand("teamcreate")).setExecutor(new TeamCreate(this));
        Objects.requireNonNull(getCommand("teaminvite")).setExecutor(new TeamInvite(this));
        Objects.requireNonNull(getCommand("teamjoin")).setExecutor(new TeamJoin(this));
        Objects.requireNonNull(getCommand("teamreload")).setExecutor(new TeamReload(this));
        Objects.requireNonNull(getCommand("teamleave")).setExecutor(new TeamLeave(this));
        Objects.requireNonNull(getCommand("teamcombat")).setExecutor(new TeamCombat(this));
        Objects.requireNonNull(getCommand("teamkick")).setExecutor(new TeamKick(this));
        Objects.requireNonNull(getCommand("teaminfo")).setExecutor(new TeamInfo(this));

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ServerHandler(this), this);

    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.YELLOW + "TeamPlus has been unloaded safely");
    }
}
