package de.unverwunderbar.legacy.legacyskywars;

import de.unverwunderbar.legacy.legacygamemanager.LegacyGameManager;
import de.unverwunderbar.legacy.legacyskywars.commands.ReloadCommand;
import de.unverwunderbar.legacy.legacyskywars.commands.StartCommand;
import de.unverwunderbar.legacy.legacyskywars.config.item.Loottables;
import de.unverwunderbar.legacy.legacyskywars.config.world.Worlds;
import de.unverwunderbar.legacy.legacyutils.Legacyutils;
import org.bukkit.plugin.java.JavaPlugin;

public final class LegacySkywars extends JavaPlugin {

    public static LegacySkywars main;

    public static Legacyutils legacyutils;

    public static LegacyGameManager legacyGameManager;

    @Override
    public void onEnable() {
        main = this;

        saveDefaultConfig();

        legacyutils = (Legacyutils) getServer().getPluginManager().getPlugin("legacyutils");
        legacyGameManager = (LegacyGameManager) getServer().getPluginManager().getPlugin("LegacyGameManager");

        Worlds.init();
        Loottables.init();

        /*
            Deprecated: LegacyGameManager already defines GameWorld utility commands
         */
        //getCommand("start").setExecutor(new StartCommand());

        getCommand("reload").setExecutor(new ReloadCommand());
    }

    @Override
    public void onDisable() {

    }
}
