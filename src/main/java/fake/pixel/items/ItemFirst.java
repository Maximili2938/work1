package fake.pixel.items;

import fake.pixel.Gul;
import fake.pixel.api.IItem;
import fake.pixel.api.itemconstructor.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class ItemFirst implements IItem {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.TNT)
                .withDisplayName("§bБомба §7(FANTASY)")
                .withLore(Arrays.asList("§7Тип: Ифритовый стержень", "§7Редкость: §cFantasy"))
                .withEnchantments(Collections.singletonMap(Enchantment.DAMAGE_ALL, 5))
                .withFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS))
                .build();
    }

    @Override
    public void onDrop(PlayerDropItemEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (event.getItemDrop().isOnGround()) {;
                    explodeTNT(event.getItemDrop().getLocation(), event.getItemDrop());
                    this.cancel();
                }
            }
        }.runTaskTimer(Gul.getInstance(), 1, 1);
    }

    private void explodeTNT(Location location, Item itemDrop) {
        TNTPrimed tnt = location.getWorld().spawn(location, TNTPrimed.class);
        tnt.setYield(0); 
        
        itemDrop.remove();
        tnt.remove();
        
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 9);
    }
}
