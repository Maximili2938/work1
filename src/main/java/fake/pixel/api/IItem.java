package fake.pixel.api;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public interface IItem {

    ItemStack getItem();


    default void onInteract(PlayerInteractEvent event) {

    }

    default void onDrop(PlayerDropItemEvent event){

    }

    default void onPicUp(PlayerPickupItemEvent event) {

    }

    default void onInteractEntity(PlayerInteractEntityEvent event) {

    }

    default void onWheel(PlayerItemHeldEvent event) {

    }

    default void onDrag(InventoryDragEvent event) {

    }

    default void onHeld(PlayerItemHeldEvent event) {

    }

    default void onItemClick(InventoryClickEvent event) {

    }
}
