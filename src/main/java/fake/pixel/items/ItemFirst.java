package fake.pixel.items;

import fake.pixel.Gul;
import fake.pixel.api.Item;
import fake.pixel.api.itemconstructor.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;

public class ItemFirst implements Item, ItemConstructor {

    @Override
    public String getId() {
        return "HYPERION";
    }

    @Override
    public String getName() {
        return "§7Hyperion";
    }

    @Override
    public Material getMaterial() {
        return Material.TNT;
    }

    @Override
    public ItemType getType() {
        return ItemType.Sword;
    }

    @Override
    public ItemRarity getRarity() {
        return ItemRarity.LEGENDARY;
    }


    @Override
    public double getStat(Stat stat) {
        switch (stat) {
            case Damage:
                return 130;
            case Strength:
                return 90;
            case Speed:
                return 300;
            default:
                return 0;
        }
    }


    @Override
    public ItemStack getItem() {
        return new ItemBuilder(getMaterial())
                .withDisplayName(getName())
                .withLore(Arrays.asList("&7Тип: Ифритовый стержень", "&aРедкость: " + getRarity().withPrefixType().toUpperCase(), "ТЕСТ"))
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

    private void explodeTNT(Location location, org.bukkit.entity.Item itemDrop) {
        TNTPrimed tnt = location.getWorld().spawn(location, TNTPrimed.class);
        tnt.setYield(0); 
        
        itemDrop.remove();
        tnt.remove();
        
        location.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, location, 9);
    }
}
