package org.betamc.homeslots;

import me.zavdav.zcore.player.OfflinePlayer;
import org.bukkit.util.config.Configuration;

import java.io.File;

public class HomeSlotsStorage extends Configuration {

    public HomeSlotsStorage(HomeSlotsPlugin plugin) {
        super(new File(plugin.getDataFolder(), "storage.yml"));
        load();
    }

    public int getAdditionalHomeSlots(OfflinePlayer player) {
        return getInt(player.getUuid().toString(), 0);
    }

    public void incrementAdditionalHomeSlots(OfflinePlayer player) {
        setProperty(player.getUuid().toString(), getAdditionalHomeSlots(player) + 1);
    }

}
