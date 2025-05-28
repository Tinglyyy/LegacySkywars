package de.unverwunderbar.legacy.legacyskywars.config.world;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.unverwunderbar.legacy.legacygamemanager.world.LobbyWorld;
import de.unverwunderbar.legacy.legacyskywars.config.ResourceLoader;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Worlds {
    @Getter
    private static final List<SkywarsWorld> worlds = new ArrayList<>();

    public static void init() {
        String json = ResourceLoader.loadWorlds();

        JsonObject root = new JsonParser().parse(new StringReader(json)).getAsJsonObject();

        worlds.clear();

        root.get("worlds").getAsJsonArray().forEach(jsonElement ->
                worlds.add(serializeWorld(jsonElement.getAsJsonObject())));
    }

    private static SkywarsWorld serializeWorld(JsonObject world) {
        World ingameWorld = Bukkit.getWorld(world.get("name").getAsString());
        World ingameLobbyWorld = Bukkit.getWorld(world.get("lobby").getAsString());

        String title = world.get("title").getAsString();

        List<Location> spawns = new ArrayList<>(),
                baseChests = new ArrayList<>(),
                middleChests = new ArrayList<>(),
                specialChests = new ArrayList<>();

        world.get("spawns").getAsJsonArray().forEach(jsonElement ->
                spawns.add(CoordinateManager.serialize(ingameWorld, jsonElement.getAsJsonArray())));

        world.get("base").getAsJsonArray().forEach(jsonElement ->
                baseChests.add(CoordinateManager.serialize(ingameWorld, jsonElement.getAsJsonArray())));

        world.get("middle").getAsJsonArray().forEach(jsonElement ->
                middleChests.add(CoordinateManager.serialize(ingameWorld, jsonElement.getAsJsonArray())));

        world.get("special").getAsJsonArray().forEach(jsonElement ->
                specialChests.add(CoordinateManager.serialize(ingameWorld, jsonElement.getAsJsonArray())));

        SkywarsWorld skywarsWorld = new SkywarsWorld(ingameWorld, null, title, baseChests, middleChests, specialChests, spawns);

        LobbyWorld lobbyWorld = new LobbyWorld(ingameLobbyWorld, skywarsWorld);

        skywarsWorld.setLobbyWorld(lobbyWorld);

        return skywarsWorld;
    }

    public static SkywarsWorld getGameWorld(World world) {
        for(SkywarsWorld w : worlds)
            if(w.getIngameWorld().equals(world))
                return w;

        return null;
    }
}
