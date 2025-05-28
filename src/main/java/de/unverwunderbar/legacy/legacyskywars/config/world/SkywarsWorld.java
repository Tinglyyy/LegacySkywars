package de.unverwunderbar.legacy.legacyskywars.config.world;

import de.unverwunderbar.legacy.legacygamemanager.world.GameWorld;
import de.unverwunderbar.legacy.legacygamemanager.world.LobbyWorld;
import de.unverwunderbar.legacy.legacyskywars.config.item.ItemRecord;
import de.unverwunderbar.legacy.legacyskywars.config.item.Loottables;
import de.unverwunderbar.legacy.legacyutils.message.impl.title.TitleTimer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.List;

import static de.unverwunderbar.legacy.legacyskywars.LegacySkywars.main;

public class SkywarsWorld extends GameWorld {

    @Getter
    private final List<Location> baseChests, middleChests, specialChests, spawns;

    @Getter
    private GameState gameState;

    @Getter
    private final String title;

    @Getter @Setter
    private boolean open;


    public SkywarsWorld(World world, LobbyWorld lobby, String title, List<Location> baseChests, List<Location> middleChests, List<Location> specialChests, List<Location> spawns) {
        super(world, lobby);
        this.title = title;

        this.gameState = GameState.STARTING;

        this.baseChests = baseChests;
        this.middleChests = middleChests;
        this.specialChests = specialChests;

        this.spawns = spawns;


    }

    private void fillChest(Location location, List<ItemRecord> inv) {
        if(location.getBlock().getState() instanceof Chest chest)
            Bukkit.getScheduler().runTaskAsynchronously(main, () -> chest.getBlockInventory().setContents(Loottables.getRandomLoot(inv)));

        else
            main.getLogger().warning("Couldn't find chest at " + location.toString());
    }

    private void generateLoot() {
        for(Location location : baseChests)
            fillChest(location, Loottables.getBaseItems());

        for(Location location : middleChests)
            fillChest(location, Loottables.getMiddleItems());

        for(Location location : specialChests)
            fillChest(location, Loottables.getSpecialItems());
    }

    @Override
    public void start() {

        if(!this.gameState.equals(GameState.STARTING)) {
            main.getLogger().severe("Attempted to start game while §l" + gameState.toString());
            return;
        }

        generateLoot();

        int i = 0;

        for(Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(spawns.get(i));
            i++;
        }

        /**
         * Set gamestate to Running on Time Finish
         */
        TitleTimer titleTimer
                = new TitleTimer("§bStartet in...", 20 * 5, 0, 20, this::setRunning);

        titleTimer.broadcast();

    }

    @Override
    public void finish() {
        //TODO: Implement finish logic
    }

    public void setRunning() {

    }

    /*
        Deprecated: LegacyGameManager already provides abstract finish method
     */
//    public void setFinishing() {
//
//    }
}
