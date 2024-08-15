package fake.pixel.api.itemconstructor;

import org.bukkit.Material;

import java.util.Map;

public interface ItemConstructor {

    String getId();

    String getName();

    Material getMaterial();

    ItemType getType();

    ItemRarity getRarity();

    interface StatProvider {
        Map<Stat, Number> stats();
    }

    default StatProvider statProvider() {
        if (this instanceof StatProvider) {
            return (StatProvider) this;
        }
        return null;
    }

    default double getStat(Stat stat) {
        StatProvider provider = statProvider();
        if (provider != null) return provider.stats().getOrDefault(stat, 0d).doubleValue();
        return 0;
    }

}