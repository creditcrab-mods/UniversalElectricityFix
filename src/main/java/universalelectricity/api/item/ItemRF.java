package universalelectricity.api.item;

import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import universalelectricity.api.energy.RFDisplay;
import universalelectricity.core.item.RFItemHelper;

import java.util.List;

public abstract class ItemRF extends Item implements IEnergyContainerItem {

    public int CAPACITY = 32000;
    public int SEND = 160;
    public  int RECEIVE = 160;

    public ItemRF() {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setNoRepair();
        //this.setUnlocalizedName("icbm:" + name);
        //this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    public void onCreated(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer) {
        RFItemHelper.setDefaultEnergyTag(itemStack, 0);
    }

    @Override
    public void addInformation(final ItemStack itemStack, final EntityPlayer par2EntityPlayer, final List par3List, final boolean par4) {
        par3List.add(RFDisplay.displayRF(this.getEnergyStored(itemStack)));
    }

    @Override
    public boolean isDamaged(ItemStack var1) {
        return true;
    }

    @Override
    public int getDisplayDamage(ItemStack item) {
        return item.stackTagCompound == null ? CAPACITY : CAPACITY - item.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxDamage(ItemStack var1) {
        return CAPACITY;
    }

    @Override
    public int receiveEnergy(ItemStack itemStack, int i, boolean b) {
        if (itemStack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(itemStack, 0);
        }

        int energy = itemStack.stackTagCompound.getInteger("Energy");
        int delta = Math.min(i, Math.min(CAPACITY - energy, RECEIVE));

        energy += delta;
        itemStack.stackTagCompound.setInteger("Energy", energy);

        return delta;
    }

    public void drainEnergy(ItemStack itemStack, int i){
        if (itemStack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(itemStack, 0);
        }
        int energy = itemStack.stackTagCompound.getInteger("Energy");
        itemStack.stackTagCompound.setInteger("Energy",energy - i);
    }

    @Override
    public int extractEnergy(ItemStack itemStack, int i, boolean b) {
        if (itemStack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(itemStack, 0);
        }

        int energy = itemStack.stackTagCompound.getInteger("Energy");
        int delta = Math.min(i, Math.min(energy,SEND));

        energy -= delta;
        itemStack.stackTagCompound.setInteger("Energy", energy);

        return delta;
    }

    @Override
    public int getEnergyStored(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(itemStack, 0);
        }

        return itemStack.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack itemStack) {
        return CAPACITY;
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {

        par3List.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(par1),0));

        par3List.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(par1),CAPACITY));
    }
}
