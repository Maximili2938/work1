package fake.pixel.api;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ActionItemStorage {

    private HashMap<ItemStack, IItem> items = new HashMap<>();

    public void register(IItem item){
        items.put(item.getItem(), item);
    }

    public HashMap<ItemStack, IItem> getItems() {
        return items;
    }

}
