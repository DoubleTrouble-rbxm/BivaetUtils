package me.bivaet.bivaetutils.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakechatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("bivaetutils.fakechat")) {
            Player p = (Player) sender;

            if (args.length < 2) {
                p.sendMessage("Вы не указали аргументы к команде.");
                p.sendMessage("Пример: /fakechat [player] [message]");
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    String word = args[1];
                    target.chat(word);
                } else {
                    p.sendMessage("Игрок не найден!");
                }
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    StringBuilder builder = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]);
                        builder.append(" ");
                    }
                    String finalMessage = builder.toString();
                    finalMessage = finalMessage.stripTrailing();
                    target.chat(finalMessage);
                } else {
                    p.sendMessage("Игрок не найден!");
                }
            }
        }
        return true;
    }
}
