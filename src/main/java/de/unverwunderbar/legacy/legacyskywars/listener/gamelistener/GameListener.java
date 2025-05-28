package de.unverwunderbar.legacy.legacyskywars.listener.gamelistener;

import de.unverwunderbar.legacy.legacygamemanager.world.GameWorld;
import de.unverwunderbar.legacy.legacyskywars.config.world.GameState;
import de.unverwunderbar.legacy.legacyskywars.config.world.SkywarsWorld;
import org.bukkit.event.Listener;

/**
 *  provides some utility for listeners
 */
public interface GameListener extends Listener {
    static boolean cancel(GameWorld world) {
        if(world == null) return false;
        if(!(world instanceof SkywarsWorld skywarsWorld)) return false;

        return skywarsWorld.getGameState().equals(GameState.STARTING);
    }
}
