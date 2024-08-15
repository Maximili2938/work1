package fake.pixel.api;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    private final ActionItemStorage storage;

    public ItemListener(ActionItemStorage storage) {
        this.storage = storage;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (storage != null) {
            ItemStack itemStack = event.getItem();
            if (itemStack != null && storage.getItems().containsKey(itemStack)) {
                storage.getItems().get(itemStack).onInteract(event);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (storage != null) {
            ItemStack itemStack = event.getItemDrop().getItemStack();
            if (itemStack != null && storage.getItems().containsKey(itemStack)) {
                storage.getItems().get(itemStack).onDrop(event);
            }
        }
    }

    @EventHandler
    public void onPicUp(PlayerPickupItemEvent event) {
        if (storage != null) {
            ItemStack itemStack = event.getItem().getItemStack();
            if (itemStack != null && storage.getItems().containsKey(itemStack)) {
                storage.getItems().get(itemStack).onPicup(event);
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event){
        if (storage != null) {
            ItemStack itemStack = event.getPlayer().getItemInHand();
            if (itemStack != null && storage.getItems().containsKey(itemStack)) {
                storage.getItems().get(itemStack).onInteractEntity(event);
            }
        }
    }

    @EventHandler
    public void onWheel(PlayerItemHeldEvent event) {
        if (storage != null) {
            ItemStack previousItem = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
            ItemStack currentItem = event.getPlayer().getInventory().getItem(event.getNewSlot());
            if (currentItem != null && storage.getItems().containsKey(currentItem)) {
                storage.getItems().get(currentItem).onWheel(event);
            } else if (previousItem != null && storage.getItems().containsKey(previousItem)) {
                storage.getItems().get(previousItem).onWheel(event);
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (storage != null) {
            for (ItemStack itemStack : event.getNewItems().values()) {
                if (itemStack != null && storage.getItems().containsKey(itemStack)) {
                    storage.getItems().get(itemStack).onDrag(event);
                }
            }
        }
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {
        if (storage != null) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && storage.getItems().containsKey(clickedItem)) {
                storage.getItems().get(clickedItem).onItemClick(event);
            }
        }
    }

    @EventHandler
    public void onHeld(PlayerItemHeldEvent event) {
        if (storage != null) {
            ItemStack itemStack = event.getPlayer().getItemInHand();
            if (itemStack != null && storage.getItems().containsKey(itemStack)) {
                storage.getItems().get(itemStack).onHeld(event);
            }
        }
    }
}
