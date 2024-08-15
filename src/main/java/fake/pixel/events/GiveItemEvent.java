package fake.pixel.events;

import fake.pixel.api.itemconstructor.Stat;
import fake.pixel.items.HyperionItem;
import fake.pixel.items.ItemSecond;
import fake.pixel.items.ItemThird;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class GiveItemEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        ItemSecond itemSecond = new ItemSecond();
        ItemThird itemThird = new ItemThird();
        HyperionItem itemFirst = new HyperionItem();

        Player player = event.getPlayer();

        player.sendMessage("You got " + itemFirst.getName() + "! And his RARITY is " + itemFirst.getRarity().withPrefixType().toUpperCase() + "Next: " + itemFirst.getRarity().next() + "" + itemFirst.getStat(Stat.SPEED) + "след ->" + itemFirst.getStat(Stat.DAMAGE));

        Inventory playerInventory = event.getPlayer().getInventory();

        playerInventory.addItem(itemThird.getItem(), itemSecond.getItem(), itemFirst.getItem());

    }
}
