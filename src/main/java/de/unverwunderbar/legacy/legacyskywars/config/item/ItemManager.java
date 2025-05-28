package de.unverwunderbar.legacy.legacyskywars.config.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static de.unverwunderbar.legacy.legacyskywars.LegacySkywars.main;

public class ItemManager {
    public static ItemRecord serialize(JsonObject item) {
        try {
            Material material = Material.valueOf(item.get("material").getAsString().toUpperCase());

            int count = item.get("count").getAsInt();

            int maxCount = item.get("maxcount").getAsInt();

            int rarity = item.get("rarity").getAsInt();

            ItemStack stack = new ItemStack(material);

            ItemMeta meta = stack.getItemMeta();

            JsonArray enchantments = item.getAsJsonArray("enchantments");

            enchantments.forEach(en -> {
                JsonObject enchant = en.getAsJsonObject();

                Enchantment enchantment = Enchantment.DURABILITY;

                for(Enchantment e : Enchantment.values())
                    if(e.getName().equalsIgnoreCase(enchant.get("id").getAsString()))
                        enchantment = e;

                int power = enchant.get("power").getAsInt();

                meta.addEnchant(enchantment, power, false);
            });

            stack.setItemMeta(meta);
            return new ItemRecord(stack, count, maxCount, rarity);
        } catch (NullPointerException ex) {
            main.getLogger().warning(ex.getMessage());
            return new ItemRecord(new ItemStack(Material.AIR), 1, 1, 1);
        }

    }
}
