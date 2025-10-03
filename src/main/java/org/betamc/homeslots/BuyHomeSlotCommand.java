package org.betamc.homeslots;

import me.zavdav.zcore.ZCore;
import me.zavdav.zcore.player.OfflinePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class BuyHomeSlotCommand implements CommandExecutor {

    private final HomeSlotsPlugin plugin;

    public BuyHomeSlotCommand(HomeSlotsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender source, Command command, String s, String[] args) {
        if (!(source instanceof Player)) {
            source.sendMessage(ChatColor.RED + "You cannot use this command as console");
            return true;
        }
        OfflinePlayer player = ZCore.getOfflinePlayer(((Player) source).getUniqueId());
        BigDecimal cost = plugin.calculateNextHomeSlotPrice(player);
        if (player.getAccount().subtract(cost)) {
            plugin.storage().incrementAdditionalHomeSlots(player);
            Bukkit.getLogger().info("[HomeSlots] " + player.getName() + " bought a home slot, they now have " + plugin.getHomeSlots(player) + " total home slots");
            source.sendMessage(ChatColor.GREEN + "You now have " + plugin.getHomeSlots(player) + " total home slots");
            source.sendMessage(ChatColor.GREEN + "The next home slot costs " + ZCore.formatCurrency(plugin.calculateNextHomeSlotPrice(player)));
        } else {
            source.sendMessage(ChatColor.RED + "You need " + ZCore.formatCurrency(cost) + " to afford the next home slot");
        }
        return true;
    }

}
