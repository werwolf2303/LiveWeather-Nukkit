package com.liveweather.bukkit;

import com.liveweather.translate.Languages;

public class ChatColor {
    /**
     * Represents black
     */
    public String BLACK = "§0";
    /**
     * Represents dark blue
     */
        public String DARK_BLUE = "§1";
    /**
     * Represents dark green
     */
    public String DARK_GREEN = "§2";
    /**
     * Represents dark blue (aqua)
     */
    public String DARK_AQUA = "§3";
    /**
     * Represents dark red
     */
    public String DARK_RED = "§4";
    /**
     * Represents dark purple
     */
    public String DARK_PURPLE = "§5";
    /**
     * Represents gold
     */
    public String GOLD = "§6";
    /**
     * Represents gray
     */
    public String GRAY = "§7";
    /**
     * Represents dark gray
     */
    public String DARK_GRAY = "§8";
    /**
     * Represents blue
     */
    public String BLUE = "§9";
    /**
     * Represents green
     */
    public String GREEN = "§a";
    /**
     * Represents aqua
     */
    public String AQUA = "§b";
    /**
     * Represents red
     */
    public String RED = "§c";
    /**
     * Represents light purple
     */
    public String LIGHT_PURPLE = "§d";
    /**
     * Represents yellow
     */
    public String YELLOW = "§e";
    /**
     * Represents white
     */
    public String WHITE = "§f";
    /**
     * Represents magical characters that change around randomly
     */
    public String MAGIC = "§k";
    /**
     * Makes the text bold.
     */
    public String BOLD = "§l";
    /**
     * Makes a line appear through the text.
     */
    public String STRIKETROUGH = "§m";
    /**
     * Makes the text appear underlined.
     */
    public String UNDERLINE = "§n";
    /**
     * Makes the text italic.
     */
    public String ITALIC = "§o";
    /**
     * Resets all previous chat colors or formats.
     */
    public String RESET = "§r";
    private static ChatColor colors;
    public synchronized static ChatColor getInstance() {
        if (colors == null) {
            colors = new ChatColor();
        }
        return colors;
    }
}
