package org.betamc.homeslots;

import me.zavdav.zcore.ZCore;
import me.zavdav.zcore.player.OfflinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeSlotsCommand implements CommandExecutor {

    private final HomeSlotsPlugin plugin;

    public HomeSlotsCommand(HomeSlotsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender source, Command command, String s, String[] args) {
        if (!(source instanceof Player)) {
            source.sendMessage(ChatColor.RED + "You cannot use this command as console");
            return true;
        }
        OfflinePlayer player = ZCore.getOfflinePlayer(((Player) source).getUniqueId());
        source.sendMessage(ChatColor.GREEN + "You have " + plugin.getHomeSlots(player) + " total home slots");
        source.sendMessage(ChatColor.GREEN + "The next home slot costs " + ZCore.formatCurrency(plugin.calculateNextHomeSlotPrice(player)));
        return true;
    }

}
