package de.unverwunderbar.legacy.legacyskywars.config.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.unverwunderbar.legacy.legacyskywars.config.ResourceLoader;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Loottables {
    private static JsonArray base, middle, special;

    @Getter
    private static List<ItemRecord> baseItems = new ArrayList<>();

    @Getter
    private static List<ItemRecord> middleItems = new ArrayList<>();

    @Getter
    private static List<ItemRecord> specialItems = new ArrayList<>();

    public static void init() {
        String json = ResourceLoader.loadLoottables();

        JsonObject root = new JsonParser().parse(new StringReader(json)).getAsJsonObject();

        base = root.getAsJsonArray("base");
        middle = root.getAsJsonArray("middle");
        special = root.getAsJsonArray("special");

        baseItems = serializeItems(base);
        middleItems = serializeItems(middle);
        specialItems = serializeItems(special);
    }

    private static List<ItemRecord> serializeItems(JsonArray jsonArray) {
        List<ItemRecord> res = new ArrayList<>();

        jsonArray.forEach(it -> {
            try {
                res.add(ItemManager.serialize(it.getAsJsonObject()));
            } catch (IllegalArgumentException ignored) {}
        });

        return res;
    }

    public static ItemStack[] getRandomLoot(List<ItemRecord> items) {
        ItemStack[] inv = new ItemStack[27];

        Random random = new Random();

        for(int i = 0; i < inv.length; i++) {
            ItemRecord r = items.get(random.nextInt(items.size()));
            int p = random.nextInt(1 + r.rarity());

            if(p != 1) continue;

            int c = random.nextInt(r.count(), r.maxCount() + 1);

            ItemStack stack = r.item().clone();

            stack.setAmount(c);

            inv[i] = stack;
        }

        return inv;
    }
}
