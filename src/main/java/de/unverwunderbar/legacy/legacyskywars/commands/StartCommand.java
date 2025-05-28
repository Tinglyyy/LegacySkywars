package de.unverwunderbar.legacy.legacyskywars.commands;

import de.unverwunderbar.legacy.legacyskywars.config.world.Worlds;
import de.unverwunderbar.legacy.legacyskywars.config.world.SkywarsWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Deprecated: LegacyGameManager already supplies GameWorld utility commands
 */
@Deprecated(since = "1.1-SNAPSHOT", forRemoval = true)
public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("§cCan't run as console!");

            return true;
        } else if (!player.hasPermission("skywars.start")) {
            sender.sendMessage("§cYou lack permission to do that!");

            return true;
        }

        SkywarsWorld world = Worlds.getGameWorld(player.getWorld());

        if(world == null) {
            player.sendMessage("§cNot currently in a game world!");
            return true;
        }

        world.start();

        return true;
    }
}
