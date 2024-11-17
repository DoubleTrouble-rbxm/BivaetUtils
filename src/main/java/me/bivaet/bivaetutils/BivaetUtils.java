package me.bivaet.bivaetutils;

import me.bivaet.bivaetutils.commands.FakechatCommand;
import me.bivaet.bivaetutils.commands.MaskCommand;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;

public final class BivaetUtils extends JavaPlugin {


    static ConsoleCommandSender console;
    static Server server;

    public static ScoreboardManager sbm;
    public static String NoTagTeamName = "NoNameTagTeam";


    @Override
    public void onEnable() {
        server = getServer();
        console = server.getConsoleSender();
        sbm = server.getScoreboardManager();

        console.sendMessage("[BivaetUtils] " + ChatColor.GREEN + "Плагин активирован! Автор плагина: Bivaet");
        getCommand("mask").setExecutor(new MaskCommand());
        getCommand("fakechat").setExecutor(new FakechatCommand());
    }

    @Override
    public void onDisable() {
        System.out.println("[BivaetUtils] " + ChatColor.GOLD + "Плагин Биваета выключен.");
    }

}
