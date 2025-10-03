package org.betamc.homeslots;

import me.zavdav.zcore.player.OfflinePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HomeSlotsPlugin extends JavaPlugin {

    private HomeSlotsConfig config;
    private HomeSlotsStorage storage;

    @Override
    public void onEnable() {
        config = new HomeSlotsConfig(this);
        storage = new HomeSlotsStorage(this);
        getCommand("buyhomeslot").setExecutor(new BuyHomeSlotCommand(this));
        getCommand("homeslots").setExecutor(new HomeSlotsCommand(this));
        Bukkit.getPluginManager().registerEvent(Event.Type.CUSTOM_EVENT, new HomeSetListener(this), Event.Priority.Lowest, this);
        Bukkit.getLogger().info("[HomeSlots] Version " + getDescription().getVersion() + " enabled.");
    }

    @Override
    public void onDisable() {
        storage.save();
        Bukkit.getLogger().info("[HomeSlots] Version " + getDescription().getVersion() + " disabled.");
    }

    public HomeSlotsConfig config() {
        return config;
    }

    public HomeSlotsStorage storage() {
        return storage;
    }

    public int getHomeSlots(OfflinePlayer player) {
        return config().initialHomeSlots() + storage().getAdditionalHomeSlots(player);
    }

    public BigDecimal calculateNextHomeSlotPrice(OfflinePlayer player) {
        return config().initialPrice().multiply(config().priceIncreaseFactor().pow(storage().getAdditionalHomeSlots(player))).setScale(2, RoundingMode.DOWN);
    }

}
