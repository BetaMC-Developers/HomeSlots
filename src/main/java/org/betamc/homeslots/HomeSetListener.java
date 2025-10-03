package org.betamc.homeslots;

import me.zavdav.zcore.ZCore;
import me.zavdav.zcore.command.event.HomeSetEvent;
import me.zavdav.zcore.player.OfflinePlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;

import java.math.BigDecimal;

public class HomeSetListener extends CustomEventListener {

    private final HomeSlotsPlugin plugin;

    public HomeSetListener(HomeSlotsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCustomEvent(Event event) {
        if (event instanceof HomeSetEvent) {
            onHomeSet((HomeSetEvent) event);
        }
    }

    private void onHomeSet(HomeSetEvent event) {
        OfflinePlayer player = event.getPlayer();
        BigDecimal cost = plugin.calculateNextHomeSlotPrice(player);
        if (player.getHomes().count() >= plugin.getHomeSlots(player)) {
            Player source = event.getSource();
            if (source.getUniqueId().equals(player.getUuid())) {
                event.cancel(ChatColor.RED + "You have reached your home limit");
                source.sendMessage(ChatColor.RED + "Use " + ChatColor.GOLD + "/buyhomeslot " + ChatColor.RED + "to buy additional home slots");
                source.sendMessage(ChatColor.GREEN + "The next home slot costs " + ZCore.formatCurrency(cost));
            } else {
                event.cancel(ChatColor.RED + player.getName() + " has reached their home limit");
            }
        }
    }

}
