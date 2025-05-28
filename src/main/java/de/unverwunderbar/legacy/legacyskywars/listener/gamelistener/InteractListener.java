package de.unverwunderbar.legacy.legacyskywars.listener.gamelistener;

import de.unverwunderbar.legacy.legacyskywars.config.world.SkywarsWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements GameListener {
    @EventHandler
    public void onEntityInteract(EntityInteractEvent e) {
        e.setCancelled(
                GameListener.cancel(SkywarsWorld.fromWorld(e.getBlock().getWorld()))
        );
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        e.setCancelled(
                GameListener.cancel(SkywarsWorld.fromWorld(e.getPlayer().getWorld()))
        );
    }
}
