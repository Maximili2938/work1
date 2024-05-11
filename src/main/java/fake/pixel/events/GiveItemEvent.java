package fake.pixel.events;

import fake.pixel.items.ItemFirst;
import fake.pixel.items.ItemSecond;
import fake.pixel.items.ItemThird;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class GiveItemEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        ItemSecond itemSecond = new ItemSecond();
        ItemThird itemThird = new ItemThird();
        ItemFirst itemFirst = new ItemFirst();

        Inventory playerInventory = event.getPlayer().getInventory();

        playerInventory.addItem(itemThird.getItem(), itemSecond.getItem(), itemFirst.getItem());

    }
}
