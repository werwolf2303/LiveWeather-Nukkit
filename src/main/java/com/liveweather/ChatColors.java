package com.liveweather;

public enum ChatColors {
    /**
     * Represents black
     */
    BLACK("§0"),
    /**
     * Represents dark blue
     */
    DARK_BLUE("§1"),
    /**
     * Represents dark green
     */
    DARK_GREEN("§2"),
    /**
     * Represents dark blue (aqua)
     */
    DARK_AQUA("§3"),
    /**
     * Represents dark red
     */
    DARK_RED("§4"),
    /**
     * Represents dark purple
     */
    DARK_PURPLE("§5"),
    /**
     * Represents gold
     */
    GOLD("§6"),
    /**
     * Represents gray
     */
    GRAY("§7"),
    /**
     * Represents dark gray
     */
    DARK_GRAY("§8"),
    /**
     * Represents blue
     */
    BLUE("§9"),
    /**
     * Represents green
     */
    GREEN("§a"),
    /**
     * Represents aqua
     */
    AQUA("§b"),
    /**
     * Represents red
     */
    RED("§c"),
    /**
     * Represents light purple
     */
    LIGHT_PURPLE("§d"),
    /**
     * Represents yellow
     */
    YELLOW("§e"),
    /**
     * Represents white
     */
    WHITE("§f"),
    /**
     * Represents magical characters that change around randomly
     */
    MAGIC("§k"),
    /**
     * Makes the text bold.
     */
    BOLD("§l"),
    /**
     * Makes a line appear through the text.
     */
    STRIKETROUGH("§m"),
    /**
     * Makes the text appear underlined.
     */
    UNDERLINE("§n"),
    /**
     * Makes the text italic.
     */
    ITALIC("§o"),
    /**
     * Resets all previous chat colors or formats.
     */
    RESET("§r");
    
    private final String internalValue;
    ChatColors(String internalValue) {
        this.internalValue = internalValue;
    }
    String getCode() {
        return internalValue;
    }
}
