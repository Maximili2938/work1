package fake.pixel.api;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ActionItemStorage {

    private HashMap<ItemStack, Item> items = new HashMap<>();

    public void register(Item item){
        items.put(item.getItem(), item);
    }

    public HashMap<ItemStack, Item> getItems() {
        return items;
    }

}
