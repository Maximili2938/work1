package fake.pixel;

import fake.pixel.api.ActionItemStorage;
import fake.pixel.api.ItemListener;
import fake.pixel.events.GiveItemEvent;
import fake.pixel.items.HyperionItem;
import fake.pixel.items.ItemSecond;
import fake.pixel.items.ItemThird;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Gul extends JavaPlugin {

    private static Gul instance;

    @Override
    public void onEnable() {
        instance = this;

        register();

    }

    public static Gul getInstance() {
        return instance;
    }

    public void register(){
        ActionItemStorage storage = new ActionItemStorage();

        ItemThird itemThird = new ItemThird();
        storage.register(itemThird);

        ItemSecond itemSecond = new ItemSecond();
        storage.register(itemSecond);

        HyperionItem itemFirst = new HyperionItem();
        storage.register(itemFirst);

        ItemListener itemListener = new ItemListener(storage);
        Bukkit.getPluginManager().registerEvents(itemListener, this);

        GiveItemEvent giveItemEvent = new GiveItemEvent();
        Bukkit.getPluginManager().registerEvents(giveItemEvent, this);
    }
}
