package universalelectricity.prefab.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFProducer extends TileEntityDisableable implements IEnergyProvider {

    public EnergyStorage energyStorage;

    public TileEntityRFProducer(int capacity, int send, int receive) {
        super();
        energyStorage = new EnergyStorage(capacity,send,receive);
    }
    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        int prevEnergy = energyStorage.getEnergyStored();
        int extractedEnergy = energyStorage.extractEnergy(maxExtract,simulate);
        if(energyStorage.getEnergyStored() != prevEnergy) this.markDirty();
        return extractedEnergy;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbt){
        super.writeToNBT(nbt);
        energyStorage.writeToNBT(nbt);
    }
    @Override
    public void readFromNBT(final NBTTagCompound nbt){
        super.readFromNBT(nbt);
        energyStorage.readFromNBT(nbt);

    }
}
