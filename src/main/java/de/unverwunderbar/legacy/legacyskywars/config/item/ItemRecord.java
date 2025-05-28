package de.unverwunderbar.legacy.legacyskywars.config.item;

import org.bukkit.inventory.ItemStack;

public record ItemRecord(ItemStack item, int count, int maxCount, int rarity) {
}
