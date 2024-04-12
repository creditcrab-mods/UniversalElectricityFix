package icbm.api;

import java.lang.reflect.Method;

import icbm.api.explosion.IExplosive;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class ICBM {
    public static final String NAME = "ICBM";
    public static final String VERSION = "1.1.1";
    public static final int BLOCK_ID_PREFIX = 3880;
    public static final int ITEM_ID_PREFIX = 3900;
    public static Class explosionManager;

    public static void createExplosion(
        World worldObj, double x, double y, double z, Entity entity, int explosiveID
    ) {
        try {
            Method e = explosionManager.getMethod(
                "createExplosion",
                new Class[] { World.class,
                              Double.class,
                              Double.class,
                              Double.class,
                              Entity.class,
                              Integer.class }
            );
            e.invoke(
                (Object) null,
                new Object[] { worldObj,
                               Double.valueOf(x),
                               Double.valueOf(y),
                               Double.valueOf(z),
                               entity,
                               Integer.valueOf(explosiveID) }
            );
        } catch (Exception var10) {
            System.out.println(
                "ICBM: Failed to create an ICBM explosion with the ID: " + explosiveID
            );
            var10.printStackTrace();
        }
    }

    public static IExplosive getExplosive(String name) {
        if (name != null) {
            try {
                Method e = explosionManager.getMethod(
                    "getExplosiveByName", new Class[] { String.class }
                );
                return (IExplosive) e.invoke((Object) null, new Object[] { name });
            } catch (Exception var2) {
                System.out.println(
                    "ICBM: Failed to get explosive with the name: " + name
                );
                var2.printStackTrace();
            }
        }

        return null;
    }
}
