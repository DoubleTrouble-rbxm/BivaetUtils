package me.bivaet.bivaetutils.commands;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;
import org.bukkit.event.EventHandler;
import me.clip.placeholderapi.PlaceholderAPI;

import java.util.Random;

import static me.bivaet.bivaetutils.BivaetUtils.*;

@SuppressWarnings("deprecation")


public class MaskCommand  implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = null;
        if (sender instanceof Player) {
            if (sender.hasPermission("bivaetutils.mask")) {


                //Get the Player object to be able to do things
                p = (Player) sender;
                Random rand = new Random();
                int rand_int1 = rand.nextInt(100);
                String value = PlaceholderAPI.setPlaceholders(p, "%cmi_user_nickname%");


                if (value.equals(p.getName())) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nick Аноним#" + rand_int1 +" " + p.getName() + " -s");
                    removeNameTags(p.getName());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l[&c&lINFO&e&l] &rВы надели маску. Ваш ник скрыт на 10 минут. Теперь ваше имя - Аноним#" + rand_int1 + "."));
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nick " + p.getName() +" " + p.getName() + " -s");
                    returnNameTags(p.getName());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l[&c&lINFO&e&l] &rВы сняли маску. Теперь ваше имя - " + p.getName() + "."));
                }
            }
        } else {
            System.out.println("Только игрок может использовать эту команду!");
        }



        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        returnNameTags(event.getPlayer().getName());
    }

    private void removeNameTags(String p) {
        Team NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
        if (NoNameTagTeam == null) {
            sbm.getMainScoreboard().registerNewTeam(NoTagTeamName);
            NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
            NoNameTagTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }
        NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
        NoNameTagTeam.addEntry(p);
    }
    private void returnNameTags(String p) {
        Team NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
        if (NoNameTagTeam == null) {
            sbm.getMainScoreboard().registerNewTeam(NoTagTeamName);
            NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
            NoNameTagTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }
        NoNameTagTeam = sbm.getMainScoreboard().getTeam(NoTagTeamName);
        NoNameTagTeam.removeEntry(p);
    }
}
