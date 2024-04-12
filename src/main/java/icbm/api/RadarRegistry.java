package icbm.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import universalelectricity.core.vector.Vector2;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.vector.Region2;

public class RadarRegistry {
    private static Set<TileEntity> detectableTileEntities = new HashSet<>();
    private static Set<Entity> detectableEntities = new HashSet<>();

    public static void register(TileEntity tileEntity) {
        if (!detectableTileEntities.contains(tileEntity)) {
            detectableTileEntities.add(tileEntity);
        }
    }

    public static void unregister(TileEntity tileEntity) {
        if (detectableTileEntities.contains(tileEntity)) {
            detectableTileEntities.remove(tileEntity);
        }
    }

    public static void register(Entity entity) {
        if (!detectableEntities.contains(entity)) {
            detectableEntities.add(entity);
        }
    }

    public static void unregister(Entity entity) {
        if (detectableEntities.contains(entity)) {
            detectableEntities.remove(entity);
        }
    }

    public static List<TileEntity>
    getTileEntitiesInArea(Vector2 minVector, Vector2 maxVector) {
        ArrayList<TileEntity> returnArray = new ArrayList<>();
        cleanUpArray();

        for (TileEntity tileEntity : detectableTileEntities) {
            if ((new Region2(minVector, maxVector))
                    .isIn((new Vector3(tileEntity)).toVector2())) {
                returnArray.add(tileEntity);
            }
        }

        return returnArray;
    }

    public static List<Entity> getEntitiesWithinRadius(Vector2 vector, int radius) {
        cleanUpArray();
        ArrayList<Entity> returnArray = new ArrayList<>();

        for (Entity entity : detectableEntities) {
            if (Vector2.distance(vector, (new Vector3(entity)).toVector2())
                <= (double) radius) {
                returnArray.add(entity);
            }
        }

        return returnArray;
    }

    public static Set<TileEntity> getTileEntities() {
        cleanUpArray();
        return detectableTileEntities;
    }

    public static Set<Entity> getEntities() {
        cleanUpArray();
        return detectableEntities;
    }

    public static void cleanUpArray() {
        try {
            Iterator<TileEntity> e = detectableTileEntities.iterator();

            while (e.hasNext()) {
                TileEntity it2 = e.next();

                if (it2 == null) {
                    e.remove();
                } else if (it2.isInvalid()) {
                    e.remove();
                } else if (it2.getWorldObj().getTileEntity(it2.xCoord, it2.yCoord, it2.zCoord) != it2) {
                    e.remove();
                }
            }

            Iterator<Entity> it21 = detectableEntities.iterator();

            while (it21.hasNext()) {
                Entity entity = (Entity) it21.next();

                if (entity == null) {
                    it21.remove();
                } else if (entity.isDead) {
                    it21.remove();
                }
            }
        } catch (Exception var3) {
            System.out.println("Failed to clean up radar list properly.");
            var3.printStackTrace();
        }
    }
}
