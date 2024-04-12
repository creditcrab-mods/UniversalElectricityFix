package mffs.api.modules;

import java.util.Set;
import net.minecraft.item.ItemStack;

public interface IModuleAcceptor
{
    ItemStack getModule(final IModule p0);
    
    int getModuleCount(final IModule p0, final int... p1);
    
    Set<ItemStack> getModuleStacks(final int... p0);
    
    Set<IModule> getModules(final int... p0);
    
    int getFortronCost();
}
