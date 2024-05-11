package fake.pixel.api.itemconstructor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private Material material;
    private String displayName;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;
    private List<ItemFlag> flags;

    public ItemBuilder(Material material) {
        this.material = material;
        this.displayName = "";
        this.lore = new ArrayList<>();
        this.enchantments = new HashMap<>();
        this.flags = new ArrayList<>();
    }

    public ItemBuilder withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder withLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder withEnchantments(Map<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder withFlags(List<ItemFlag> flags) {
        this.flags = flags;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setLore(lore);

            enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));

            for (ItemFlag flag : flags) {
                meta.addItemFlags(flag);
            }

            item.setItemMeta(meta);
        }

        return item;
    }
}
