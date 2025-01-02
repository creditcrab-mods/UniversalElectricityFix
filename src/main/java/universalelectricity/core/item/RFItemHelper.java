package universalelectricity.core.item;


import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;



public class RFItemHelper {


    public static int extractEnergyFromContainer(ItemStack var0, int var1, boolean var2) {
        return isEnergyContainerItem(var0) ? ((IEnergyContainerItem)var0.getItem()).extractEnergy(var0, var1, var2) : 0;
    }

    public static int insertEnergyIntoContainer(ItemStack var0, int var1, boolean var2) {
        return isEnergyContainerItem(var0) ? ((IEnergyContainerItem)var0.getItem()).receiveEnergy(var0, var1, var2) : 0;
    }

    public static int extractEnergyFromHeldContainer(EntityPlayer var0, int var1, boolean var2) {
        ItemStack var3 = var0.getCurrentEquippedItem();
        return isEnergyContainerItem(var3) ? ((IEnergyContainerItem)var3.getItem()).extractEnergy(var3, var1, var2) : 0;
    }

    public static int insertEnergyIntoHeldContainer(EntityPlayer var0, int var1, boolean var2) {
        ItemStack var3 = var0.getCurrentEquippedItem();
        return isEnergyContainerItem(var3) ? ((IEnergyContainerItem)var3.getItem()).receiveEnergy(var3, var1, var2) : 0;
    }

    public static boolean isPlayerHoldingEnergyContainerItem(EntityPlayer var0) {
        return isEnergyContainerItem(var0.getCurrentEquippedItem());
    }

    public static boolean isEnergyContainerItem(ItemStack var0) {
        return var0 != null && var0.getItem() instanceof IEnergyContainerItem;
    }

    public static ItemStack setDefaultEnergyTag(ItemStack var0, int var1) {
        if (var0.stackTagCompound == null) {
            var0.setTagCompound(new NBTTagCompound());
        }

        var0.stackTagCompound.setInteger("Energy", var1);
        return var0;
    }
}
