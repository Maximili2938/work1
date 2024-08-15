package fake.pixel.api.itemconstructor;

import lombok.Getter;

@Getter
public enum Stat {
    HEALTH("Health", "❤", "§c", false, 100d, -1d),
    DEFENSE("Defense", "❈", "§a", false),
    SPEED("Speed", "✦", "§f", false, 100, 500),
    STRENGTH("Strength", "❁", "§c", false),
    INTELLIGENCE("Intelligence", "✎", "§b", false),
    CRIT_CHANCE("Crit Chance", "☣", "§9", true, 30, -1),
    CRIT_DAMAGE("Crit Damage", "☠", "§9", true, 50, -1),
    ATTACK_SPEED("Bonus Attack Speed", "⚔", "§e", true, 0, 100),
    ABILITY_DAMAGE("Ability Damage", "๑", "§c", true),
    MAGIC_FIND("Magic Find", "✯", "§b", false, 0, 900),
    PET_LUCK("Pet Luck", "♣", "§d", false),
    TRUE_DEFENSE("True Defense", "❂", "§f", false),
    SEA_CREATURE_CHANCE("Sea Creature Chance", "α", "§3", true, 20, -1),
    FEROCITY("Ferocity", "⫽", "§c", false, 0, 500),
    MINING_SPEED("Mining Speed", "⸕", "§6", false),
    MINING_FORTUNE("Mining Fortune", "☘", "§6", false),
    FARMING_FORTUNE("Farming Fortune", "☘", "§6", false),
    WHEAT_FORTUNE(true, "Wheat Fortune", "☘", "§6"),
    CARROT_FORTUNE(true, "Carrot Fortune", "☘", "§6"),
    POTATO_FORTUNE(true, "Potato Fortune", "☘", "§6"),
    PUMPKIN_FORTUNE(true, "Pumpkin Fortune", "☘", "§6"),
    MELON_FORTUNE(true, "Melon Fortune", "☘", "§6"),
    MUSHROOM_FORTUNE(true, "Mushroom Fortune", "☘", "§6"),
    CACTUS_FORTUNE(true, "Cactus Fortune", "☘", "§6"),
    SUGAR_CANE_FORTUNE(true, "Sugar Cane Fortune", "☘", "§6"),
    NETHER_WART_FORTUNE(true, "Nether Wart Fortune", "☘", "§6"),
    COCOA_BEANS_FORTUNE(true, "Cocoa Beans Fortune", "☘", "§6"),
    FORAGING_FORTUNE("Foraging Fortune", "☘", "§6", false),
    BREAKING_POWER("Breaking Power", "Ⓟ", "§2", false),
    PRISTINE("Pristine", "✧", "§5", false),
    FISHING_SPEED("Fishing Speed", "☂", "§b", false, 0, 500),
    COLD_RESISTANCE("Cold Resistance", "❄", "§b", false),
    BONUS_PEST_CHANCE("Bonus Pest Chance", "❄", "§2", false),
    HEALTH_REGEN("Health Regen", "❣", "§c", false, 100, -1),
    VITALITY("Vitality", "♨", "§4", false, 100, -1),
    MENDING("Mending", "☄", "§a", false, 100, -1),
    SWING_RANGE("Swing Range", "Ⓢ", "§e", false, 3, 15),
    COMBAT_WISDOM("Combat Wisdom", "☯", "§3", false, 0, -1),
    MINING_WISDOM("Mining Wisdom", "☯", "§3", false, 0, -1),
    FARMING_WISDOM("Farming Wisdom", "☯", "§3", false, 0, -1),
    FORAGING_WISDOM("Foraging Wisdom", "☯", "§3", false, 0, -1),
    FISHING_WISDOM("Fishing Wisdom", "☯", "§3", false, 0, -1),
    ENCHANTING_WISDOM("Enchanting Wisdom", "☯", "§3", false, 0, -1),
    ALCHEMY_WISDOM("Alchemy Wisdom", "☯", "§3", false, 0, -1),
    CARPENTRY_WISDOM("Carpentry Wisdom", "☯", "§3", false, 0, -1),
    RUNECRAFTING_WISDOM("Runecrafting Wisdom", "☯", "§3", false, 0, -1),
    SOCIAL_WISDOM("Social Wisdom", "☯", "§3", false, 0, -1),
    TAMING_WISDOM("Taming Wisdom", "☯", "§3", false, 0, -1),
    DAMAGE(true, "Damage", "", "§c");

    private final String name;
    private final String symbol;
    private final String prefix;
    private final boolean percentValue;
    private final double baseValue;
    private final double maxValue;
    private boolean hiddenStat;

    Stat(String name, String symbol, String prefix, boolean percentValue, double baseValue, double maxValue) {
        this.name = name;
        this.symbol = symbol;
        this.prefix = prefix;
        this.percentValue = percentValue;
        this.baseValue = baseValue;
        this.maxValue = maxValue;
        this.hiddenStat = false;
    }

    Stat(boolean hiddenStat, String name, String symbol, String prefix) {
        this(name, symbol, prefix, false);
        this.hiddenStat = hiddenStat;
    }

    Stat(String name, String symbol, String prefix, boolean percentValue) {
        this(name, symbol, prefix, percentValue, 0, -1);
    }
}
