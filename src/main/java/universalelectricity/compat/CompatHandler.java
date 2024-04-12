package universalelectricity.compat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.tileentity.TileEntity;
import universalelectricity.api.CompatibilityModule;
import universalelectricity.compat.ic2.IC2CompatModule;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.prefab.tile.ElectricTileDriver;

public class CompatHandler {

    static Map<TileEntity, ElectricTileDriver> ticklist = new ConcurrentHashMap<>();

    public static void initCompatHandlers() {
        FMLCommonHandler.instance().bus().register(new CompatTickHandler());
        if (UniversalElectricity.isIC2CompatActive()) {
            CompatibilityModule.register(new IC2CompatModule());
        }
    }

    public static void tick() {
        for(TileEntity t : ticklist.keySet()) {
            ElectricTileDriver driver = ticklist.get(t);
            if (!driver.tick()) {
                System.out.println("Remove driver for tile at x:" + t.xCoord + " y:" + t.yCoord + " z:" + t.zCoord);
                ticklist.remove(t);
                driver.invalidate();
            }
        }
    }

    public static void registerTile(TileEntity tile) {
        if (!ticklist.containsKey(tile) && CompatibilityModule.isHandler(tile)) {
            ticklist.put(tile, new ElectricTileDriver(tile));
        }
    }
    
}
