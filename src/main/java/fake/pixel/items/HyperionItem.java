package fake.pixel.items;

import fake.pixel.api.Item;
import fake.pixel.api.itemconstructor.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class HyperionItem implements Item, ItemConstructor {

    @Override
    public String getId() {
        return "HYPERION";
    }

    //
    @Override
    public String getName() {
        return "§7Hyperion";
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_SWORD;
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

        Player player = event.getPlayer();
        player.sendMessage("Hello!");

    }
}
