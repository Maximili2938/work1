package fake.pixel.api.itemconstructor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ItemBuilder {

    private Material material;
    private String displayName = "";
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments = new HashMap<>();
    private List<ItemFlag> flags;

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(displayName);
            meta.setLore(lore);

            enchantments.forEach((enchantment, level) -> meta.addEnchant(enchantment, level, true));

            if (flags != null) {
                flags.forEach(meta::addItemFlags);
            }

            item.setItemMeta(meta);
        }

        return item;
    }
}
