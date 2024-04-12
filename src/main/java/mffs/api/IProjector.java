package mffs.api;

import net.minecraft.inventory.IInventory;

public interface IProjector extends IInventory, IBiometricIdentifierLink, IFieldInteraction
{
    void projectField();
    
    void destroyField();
    
    int getProjectionSpeed();
    
    long getTicks();
}
