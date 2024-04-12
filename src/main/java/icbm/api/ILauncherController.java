package icbm.api;

import icbm.api.IBlockFrequency;
import icbm.api.IMissile;
import icbm.api.LauncherType;
import net.minecraft.item.ItemStack;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.implement.IRedstoneReceptor;

public interface ILauncherController
    extends IElectricityStorage, IRedstoneReceptor, IBlockFrequency {
    LauncherType getLauncherType();

    void launch();

    boolean canLaunch();

    String getStatus();

    Vector3 getTarget();

    void setTarget(Vector3 var1);

    void placeMissile(ItemStack var1);

    IMissile getMissile();
}
