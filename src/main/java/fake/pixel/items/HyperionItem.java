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

    private static final String ID = "HYPERION";
    private static final String NAME = "§7Hyperion";
    private static final Material MATERIAL = Material.DIAMOND_SWORD;
    private static final ItemType TYPE = ItemType.Sword;
    private static final ItemRarity RARITY = ItemRarity.LEGENDARY;

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Material getMaterial() {
        return MATERIAL;
    }

    @Override
    public ItemType getType() {
        return TYPE;
    }

    @Override
    public ItemRarity getRarity() {
        return RARITY;
    }

    @Override
    public double getStat(Stat stat) {
        switch (stat) {
            case DAMAGE:
                return 130;
            case STRENGTH:
                return 90;
            case SPEED:
                return 300;
            default:
                return 0;
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(getMaterial())
                .withDisplayName(getName())
                .withLore(Arrays.asList("§7Тип: Ифритовый стержень", "§aРедкость: " + getRarity().withPrefixType().toUpperCase(), "§7ТЕСТ"))
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
