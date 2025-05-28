package de.unverwunderbar.legacy.legacyskywars.config;

import java.io.*;

import static de.unverwunderbar.legacy.legacyskywars.LegacySkywars.main;

public class ResourceLoader {

    private static File[] resources = main.getDataFolder().listFiles();

    private static void createFiles() {
        try {
            resources = new File[2];
            resources[0] = new File("loottables.json");
            resources[0].createNewFile();

            resources[1] = new File("worlds.json");
            resources[1].createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reloadFiles() {
        resources = main.getDataFolder().listFiles();
    }

    private static BufferedReader getReader(String resource) {
        if(resources == null)
            createFiles();

        for(File f : resources)
            if(f.getName().equals(resource)) {
                try {
                    return new BufferedReader(new FileReader(f));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        return null;

    }

    public static String loadLoottables() {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = getReader("loottables.json");
            for (String line; (line = reader.readLine()) != null;)
                sb.append(line).append('\n');
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public static String loadWorlds() {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader reader = getReader("worlds.json");
            for (String line; (line = reader.readLine()) != null;)
                sb.append(line).append('\n');
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }
}
