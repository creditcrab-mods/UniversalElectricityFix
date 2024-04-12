package mffs.api.security;

import java.util.Set;
import net.minecraft.item.ItemStack;
import mffs.api.IActivatable;
import mffs.api.IBiometricIdentifierLink;
import mffs.api.modules.IModuleAcceptor;
import mffs.api.fortron.IFortronFrequency;
import net.minecraft.inventory.IInventory;

public interface IInterdictionMatrix extends IInventory, IFortronFrequency, IModuleAcceptor, IBiometricIdentifierLink, IActivatable
{
    int getWarningRange();
    
    int getActionRange();
    
    boolean mergeIntoInventory(final ItemStack p0);
    
    Set<ItemStack> getFilteredItems();
    
    boolean getFilterMode();
    
    int getFortronCost();
}
