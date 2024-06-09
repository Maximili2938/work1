package core.fel.craft;

import core.fel.Fel;
import org.apache.logging.log4j.core.net.Priority;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CraftTableCommand implements CommandExecutor {

    public static final Map<String, ItemStack> recipes = new HashMap<>();
    private final int[] craftingSlots = {11, 12, 13, 20, 21, 22, 29, 30, 31};

    public CraftTableCommand() {
        Bukkit.getPluginCommand("createmenu").setExecutor(this);

        Iterator<Recipe> iterator = Bukkit.getServer().recipeIterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next();
            if (recipe instanceof ShapedRecipe) {
                ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
                String key = getCroppedRecipeKey(shapedRecipe);
                recipes.put(key, shapedRecipe.getResult());
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Inventory inventory = player.getOpenInventory().getTopInventory();
                    checkCraftTable(inventory);
                }
            }
        }.runTaskTimer(Fel.getInstance(), 0L, 2L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            createInventory(player);
            return true;
        }
        return false;
    }

    public void createInventory(Player player) {
        Inventory craftTableMenu = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Craft Table");
        setupCraftingSlots(craftTableMenu);
        player.openInventory(craftTableMenu);
    }

    private void setupCraftingSlots(Inventory inventory) {
        // Define crafting slots
        for (int slot : craftingSlots) {
            inventory.setItem(slot, null);
        }

        inventory.setItem(24, null);
    }

    private void checkCraftTable(Inventory inventory) {
        if (inventory.getName().equals(ChatColor.GREEN + "Craft Table")) {
            boolean hasIngredients = checkForIngredients(inventory);
            if (hasIngredients) {
                String key = getCurrentCroppedCraftingKey(inventory);
                inventory.setItem(24, recipes.getOrDefault(key, null));
            } else {
                inventory.setItem(24, null);
            }
        }
    }

    private boolean checkForIngredients(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item != null && !item.getType().equals(Material.AIR)) {
                return true;
            }
        }
        return false;
    }

    private String getCroppedRecipeKey(ShapedRecipe shapedRecipe) {
        StringBuilder key = new StringBuilder();
        String[] shape = shapedRecipe.getShape();
        Map<Character, ItemStack> ingredientMap = shapedRecipe.getIngredientMap();

        int minX = 3, minY = 3, maxX = 0, maxY = 0;
        boolean found = false;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length(); col++) {
                char c = shape[row].charAt(col);
                ItemStack item = ingredientMap.get(c);
                if (item != null) {
                    found = true;
                    if (row < minY) minY = row;
                    if (row > maxY) maxY = row;
                    if (col < minX) minX = col;
                    if (col > maxX) maxX = col;
                }
            }
        }

        if (!found) return "";

        for (int row = minY; row <= maxY; row++) {
            for (int col = minX; col <= maxX; col++) {
                char c = shape[row].charAt(col);
                ItemStack item = ingredientMap.get(c);
                if (item != null) {
                    // Include only base material type
                    key.append(item.getType().toString().split(":")[0]);
                } else {
                    key.append("NULL");
                }
                key.append(";");
            }
            key.append("|");
        }

        return key.toString();
    }

    private String getCurrentCroppedCraftingKey(Inventory inventory) {
        StringBuilder key = new StringBuilder();
        ItemStack[][] grid = new ItemStack[3][3];

        for (int i = 0; i < craftingSlots.length; i++) {
            int slot = craftingSlots[i];
            int row = i / 3;
            int col = i % 3;
            grid[row][col] = inventory.getItem(slot);
        }

        int minX = 3, minY = 3, maxX = 0, maxY = 0;
        boolean found = false;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] != null) {
                    found = true;
                    if (row < minY) minY = row;
                    if (row > maxY) maxY = row;
                    if (col < minX) minX = col;
                    if (col > maxX) maxX = col;
                }
            }
        }

        if (!found) return "";

        for (int row = minY; row <= maxY; row++) {
            for (int col = minX; col <= maxX; col++) {
                ItemStack item = grid[row][col];
                if (item != null) {
                    // Include only base material type
                    key.append(item.getType().toString().split(":")[0]);
                } else {
                    key.append("NULL");
                }
                key.append(";");
            }
            key.append("|");
        }

        return key.toString();
    }
}
