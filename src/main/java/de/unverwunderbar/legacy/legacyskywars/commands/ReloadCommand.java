package de.unverwunderbar.legacy.legacyskywars.commands;

import de.unverwunderbar.legacy.legacyskywars.config.ResourceLoader;
import de.unverwunderbar.legacy.legacyskywars.config.item.Loottables;
import de.unverwunderbar.legacy.legacyskywars.config.world.Worlds;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("skywars.admin.reload")) {
            ResourceLoader.reloadFiles();

            Worlds.init();
            Loottables.init();
        }
        else
            sender.sendMessage("§cYou lack permission to do that!");

        return true;
    }
}
