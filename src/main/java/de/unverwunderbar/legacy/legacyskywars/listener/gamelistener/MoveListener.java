package de.unverwunderbar.legacy.legacyskywars.listener.gamelistener;

import de.unverwunderbar.legacy.legacyskywars.config.world.SkywarsWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Listens to PlayerMoveEvent in SkywarsWorlds to prevent movement during start
 */
public class MoveListener implements GameListener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        e.setCancelled(GameListener.cancel(SkywarsWorld.fromWorld(e.getPlayer().getWorld())));
    }
}
