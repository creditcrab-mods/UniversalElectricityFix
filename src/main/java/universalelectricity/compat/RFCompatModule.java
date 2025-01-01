package universalelectricity.compat;

import cofh.api.energy.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.api.CompatibilityModule;
import universalelectricity.api.CompatibilityType;

public class RFCompatModule extends CompatibilityModule {
    @Override
    public double doReceiveEnergy(Object handler, ForgeDirection direction, double energy, boolean doReceive) {
        if (doReceive && handler instanceof TileEnergyHandler energyHandler){
            var toReceive = CompatibilityType.REDSTONE_FLUX.fromJoules(energy);
            var receivedEnergy = energyHandler.receiveEnergy(direction,(int)toReceive,false);
            return  CompatibilityType.REDSTONE_FLUX.toJoules(receivedEnergy);
        }
        return 0;
    }

    @Override
    public double doExtractEnergy(Object handler, ForgeDirection direction, double energy, boolean doExtract) {
        if(doExtract && handler instanceof TileEnergyHandler energyHandler){
            var toExtract = CompatibilityType.REDSTONE_FLUX.fromJoules(energy);
            var extractedEnergy = energyHandler.extractEnergy(direction,(int)toExtract,false);
            return CompatibilityType.REDSTONE_FLUX.toJoules(extractedEnergy);
        }
        return 0;
    }

    @Override
    public double doChargeItem(ItemStack itemStack, double joules, boolean docharge) {
        if(docharge && itemStack.getItem() instanceof IEnergyContainerItem energyItem){
            var toCharge = CompatibilityType.REDSTONE_FLUX.fromJoules(joules);
            var energy = energyItem.receiveEnergy(itemStack,(int)toCharge,false);
            return CompatibilityType.REDSTONE_FLUX.toJoules(energy);
        }
        return 0;
    }

    @Override
    public double doDischargeItem(ItemStack itemStack, double joules, boolean doDischarge) {
        if(doDischarge && itemStack.getItem() instanceof IEnergyContainerItem energyItem){
            var toDischarge = CompatibilityType.REDSTONE_FLUX.fromJoules(joules);
            var energy = energyItem.extractEnergy(itemStack,(int)toDischarge,false);
            return CompatibilityType.REDSTONE_FLUX.toJoules(energy);
        }
        return 0;
    }

    @Override
    public boolean doIsHandler(Object obj) {
        if(obj instanceof IEnergyConnection) return true;
        if(obj instanceof IEnergyContainerItem) return true;
        return false;
    }

    @Override
    public boolean doIsEnergyContainer(Object obj) {
        return obj instanceof IEnergyReceiver;
    }

    @Override
    public double doGetEnergy(Object obj, ForgeDirection direction) {
        if(obj instanceof IEnergyHandler handler){
            return CompatibilityType.REDSTONE_FLUX.toJoules( handler.getEnergyStored(direction));
        }
        return 0;
    }

    @Override
    public boolean doCanConnect(Object obj, ForgeDirection direction, Object source) {

        if (obj instanceof IEnergyHandler handler){
            return handler.canConnectEnergy(direction);
        }
        else if (obj instanceof IEnergyReceiver receiver){
            return receiver.canConnectEnergy(direction);
        }
        else if (obj instanceof IEnergyProvider provider){
            return provider.canConnectEnergy(direction);
        }
        return false;


    }

    @Override
    public ItemStack doGetItemWithCharge(ItemStack itemStack, double energy) {
        return null;
    }

    @Override
    public double doGetMaxEnergy(Object handler, ForgeDirection direction) {
        return handler instanceof IEnergyReceiver receiver ? receiver.getMaxEnergyStored(direction) : 0;
    }

    @Override
    public double doGetEnergyItem(ItemStack is) {
        if(is.getItem() instanceof IEnergyContainerItem){
            return CompatibilityType.REDSTONE_FLUX.toJoules(((IEnergyContainerItem) is.getItem()).getEnergyStored(is));
        }
        else{
            return 0;
        }

    }

    @Override
    public double doGetMaxEnergyItem(ItemStack is) {
        if(is.getItem() instanceof IEnergyContainerItem){
            return CompatibilityType.REDSTONE_FLUX.toJoules(((IEnergyContainerItem) is.getItem()).getMaxEnergyStored(is));
        }
        else{
            return 0;
        }
    }

    @Override
    public double doGetInputVoltage(Object handler) {
        return 0;
    }

    @Override
    public double doGetOutputVoltage(Object handler) {
        return 0;
    }

    @Override
    public boolean doCanReceive(Object handler, ForgeDirection side) {
        //boolean canConnect = handler instanceof IEnergyReceiver;
        //return canConnect;
        return handler instanceof IEnergyReceiver connection && connection.canConnectEnergy(side);
    }

    @Override
    public boolean doCanExtract(Object handler, ForgeDirection side) {
        return handler instanceof IEnergyProvider connection && connection.canConnectEnergy(side);
    }

    @Override
    public double doGetDemandedJoules(Object handler) {
        return handler instanceof IEnergyReceiver receiver ? CompatibilityType.REDSTONE_FLUX.toJoules(receiver.getMaxEnergyStored(ForgeDirection.UNKNOWN) - receiver.getEnergyStored(ForgeDirection.UNKNOWN)) : 0;
    }

    @Override
    public double doGetProvidedJoules(Object handler) {
        return handler instanceof IEnergyProvider receiver ? CompatibilityType.REDSTONE_FLUX.toJoules(receiver.extractEnergy(ForgeDirection.UNKNOWN,9999,false)) : 0;
    }
}
