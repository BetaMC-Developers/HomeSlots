package org.betamc.homeslots;

import org.bukkit.util.config.Configuration;

import java.io.File;
import java.math.BigDecimal;

public class HomeSlotsConfig extends Configuration {

    private final int initialHomeSlots;
    private final BigDecimal initialPrice;
    private final BigDecimal priceIncreaseFactor;

    public HomeSlotsConfig(HomeSlotsPlugin plugin) {
        super(new File(plugin.getDataFolder(), "config.yml"));
        load();
        initialHomeSlots = getInt("initial-home-slots", 5);
        initialPrice = BigDecimal.valueOf(getDouble("initial-price", 100.0));
        priceIncreaseFactor = BigDecimal.valueOf(getDouble("price-increase-factor", 1.1));
        save();
    }

    public int initialHomeSlots() {
        return initialHomeSlots;
    }

    public BigDecimal initialPrice() {
        return initialPrice;
    }

    public BigDecimal priceIncreaseFactor() {
        return priceIncreaseFactor;
    }

}
