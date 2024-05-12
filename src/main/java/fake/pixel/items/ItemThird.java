package fake.pixel.items;

import fake.pixel.api.IItem;
import fake.pixel.api.itemconstructor.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;

public class ItemThird implements IItem {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.BLAZE_ROD)
                .withDisplayName("§bИфритовый стержень §7(RARE)")
                .withLore(Arrays.asList("§7Тип: Ифритовый стержень", "§7Редкость: §eRARE"))
                .withEnchantments(Collections.singletonMap(Enchantment.DAMAGE_ALL, 5))
                .withFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS))
                .build();
    }

    @Override
    public void onInteractEntity(PlayerInteractEntityEvent interactionEvent) {
        if (interactionEvent.getRightClicked() instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) interactionEvent.getRightClicked();
            Player player = interactionEvent.getPlayer();
            if (player.hasPermission("blindness.use")) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
                player.sendMessage("Ты выдал эффект для сущности " + entity.getName() + ".");
            }
        }
    }
}
