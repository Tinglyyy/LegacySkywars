package de.unverwunderbar.legacy.legacyskywars.config.world;

import com.google.gson.JsonArray;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class CoordinateManager {
    public static Location serialize(String worldName, JsonArray location) {
        return serialize(Bukkit.getWorld(worldName), location);
    }

    public static Location serialize(World world, JsonArray location) {

        double[] coords = new double[3];

        for(int i = 0; i < 3; i++)
            coords[i] = location.get(i).getAsDouble();

        return new Location(world, coords[0], coords[1], coords[2]);

    }
}
