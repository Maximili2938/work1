package fake.pixel.events;

import fake.pixel.api.itemconstructor.Stat;
import fake.pixel.items.ItemFirst;
import fake.pixel.items.ItemSecond;
import fake.pixel.items.ItemThird;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.util.Locale;

public class GiveItemEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        ItemSecond itemSecond = new ItemSecond();
        ItemThird itemThird = new ItemThird();
        ItemFirst itemFirst = new ItemFirst();

        Player player = event.getPlayer();

        player.sendMessage("You got " + itemFirst.getName() + "! And his RARITY is " + itemFirst.getRarity().withPrefixType().toUpperCase() + "Next: " + itemFirst.getRarity().next() + "" + itemFirst.getStat(Stat.Speed) + "след ->" + itemFirst.getStat(Stat.Damage));

        Inventory playerInventory = event.getPlayer().getInventory();

        playerInventory.addItem(itemThird.getItem(), itemSecond.getItem(), itemFirst.getItem());

    }
}
