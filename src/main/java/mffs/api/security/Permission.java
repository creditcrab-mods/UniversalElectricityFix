package mffs.api.security;

public class Permission
{
    public static final Permission FORCE_FIELD_WARP;
    public static final Permission BLOCK_ALTER;
    public static final Permission BLOCK_ACCESS;
    public static final Permission SECURITY_CENTER_CONFIGURE;
    public static final Permission BYPASS_INTERDICTION_MATRIX;
    public static final Permission DEFENSE_STATION_CONFISCATION;
    public static final Permission REMOTE_CONTROL;
    private static Permission[] LIST;
    public final int id;
    public final String name;
    
    public Permission(final int id, final String name) {
        this.id = id;
        this.name = name;
        if (Permission.LIST == null) {
            Permission.LIST = new Permission[7];
        }
        Permission.LIST[this.id] = this;
    }
    
    public static Permission getPermission(final int id) {
        if (id < Permission.LIST.length && id >= 0) {
            return Permission.LIST[id];
        }
        return null;
    }
    
    public static Permission[] getPermissions() {
        return Permission.LIST;
    }
    
    static {
        FORCE_FIELD_WARP = new Permission(0, "warp");
        BLOCK_ALTER = new Permission(1, "blockPlaceAccess");
        BLOCK_ACCESS = new Permission(2, "blockAccess");
        SECURITY_CENTER_CONFIGURE = new Permission(3, "configure");
        BYPASS_INTERDICTION_MATRIX = new Permission(4, "bypassDefense");
        DEFENSE_STATION_CONFISCATION = new Permission(5, "bypassConfiscation");
        REMOTE_CONTROL = new Permission(6, "remoteControl");
    }
}
