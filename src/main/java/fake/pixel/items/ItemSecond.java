package fake.pixel.items;

import fake.pixel.Gul;
import fake.pixel.api.Item;
import fake.pixel.api.itemconstructor.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemSecond implements Item {

    private final Map<Player, BukkitRunnable> flameTasks = new HashMap<>();
    private static final double PI = Math.PI;

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.BOOK)
                .withDisplayName("§bГримуар Огня §7(Unique)")
                .withLore(Arrays.asList("§7Тип: Книга", "§7Редкость: §6Unique"))
                .withEnchantments(Collections.singletonMap(Enchantment.LUCK, 5))
                .withFlags(Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS))
                .build();
    }

    @Override
    public void onWheel(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack previousItem = player.getInventory().getItem(event.getPreviousSlot());
        ItemStack currentItem = player.getInventory().getItem(event.getNewSlot());
        if (currentItem != null && currentItem.isSimilar(getItem())) {
            applyFireEffectsAndParticles(player, true);
        } else if (previousItem != null && previousItem.isSimilar(getItem())) {
            applyFireEffectsAndParticles(player, false);
        }
    }

    private void applyFireEffects(Player player, Location playerLocation) {
        World world = player.getWorld();
        double radiusSquared = 4.0;
        double minRadiusSquared = 2.25;
        for (Entity entity : player.getNearbyEntities(2.0, 2.0, 2.0)) {
            if (entity instanceof LivingEntity && !entity.equals(player)) {
                double distanceSquared = entity.getLocation().distanceSquared(playerLocation);
                if (distanceSquared <= radiusSquared && distanceSquared >= minRadiusSquared) {
                    entity.setFireTicks(20 * 5);
                }
            }
        }
    }

    private void removeFireEffects(Player player) {
        player.getWorld().getNearbyEntities(player.getLocation(), 2.0, 2.0, 2.0)
                .stream()
                .filter(entity -> entity instanceof LivingEntity && !entity.equals(player))
                .forEach(entity -> entity.setFireTicks(0));
    }

    public static void spawnFlameParticles(Player player) {
        Location playerLocation = player.getLocation();
        World world = playerLocation.getWorld();

        int numberOfParticles = 20;
        double triangleSize = 2.0;
        double angleIncrement = 2 * PI / numberOfParticles;
        double baseAngle = PI / 4;

        for (int i = 0; i < numberOfParticles; i++) {
            double angle = baseAngle + i * angleIncrement;
            double x = playerLocation.getX() + triangleSize * Math.cos(angle);
            double y = playerLocation.getY() + 0.5;
            double z = playerLocation.getZ() + triangleSize * Math.sin(angle);
            world.spawnParticle(Particle.FLAME, x, y, z, 10, 0, 0, 0, 0);
        }

        double centerX = playerLocation.getX();
        double centerY = playerLocation.getY() + 0.5;
        double centerZ = playerLocation.getZ();

        int numberOfCircleParticles = 30;
        double circleRadius = 1.5;
        double circleAngleIncrement = 2 * PI / numberOfCircleParticles;

        for (int i = 0; i < numberOfCircleParticles; i++) {
            double angle = i * circleAngleIncrement;
            double x = centerX + circleRadius * Math.cos(angle);
            double y = centerY;
            double z = centerZ + circleRadius * Math.sin(angle);
            world.spawnParticle(Particle.REDSTONE, x, y, z, 1, 0, 0, 0, 0);
        }
    }


    private void applyFireEffectsAndParticles(Player player, boolean apply) {
        if (apply) {
            if (!flameTasks.containsKey(player)) {
                BukkitRunnable task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.isOnline() && player.getInventory().getItemInMainHand().isSimilar(getItem())) {
                            spawnFlameParticles(player);
                            applyFireEffects(player, player.getLocation());
                        } else {
                            cancel();
                            flameTasks.remove(player);
                        }
                    }
                };
                flameTasks.put(player, task);
                task.runTaskTimer(Gul.getInstance(), 0, 2);
            }
        } else {
            BukkitRunnable task = flameTasks.remove(player);
            if (task != null) {
                task.cancel();
            }
            removeFireEffects(player);
        }
    }
}
