package icbm.api;

import universalelectricity.prefab.flag.FlagRegistry;

public class ICBMFlags {
    public static final String FLAG_BAN_GLOBAL = FlagRegistry.registerFlag("ban_icbm");
    public static final String FLAG_BAN_EXPLOSIVE
        = FlagRegistry.registerFlag("ban_explosive");
    public static final String FLAG_BAN_GRENADE
        = FlagRegistry.registerFlag("ban_grenade");
    public static final String FLAG_BAN_MISSILE
        = FlagRegistry.registerFlag("ban_missile");
    public static final String FLAG_BAN_MINECART
        = FlagRegistry.registerFlag("ban_minecart");
}
