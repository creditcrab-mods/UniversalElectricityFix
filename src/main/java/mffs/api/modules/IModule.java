package mffs.api.modules;

import mffs.api.IFieldInteraction;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;
import java.util.Set;
import mffs.api.IProjector;

public interface IModule
{
    float getFortronCost(final float p0);
    
    boolean onProject(final IProjector p0, final Set<Vector3> fieldBlocks);
    
    int onProject(final IProjector p0, final Vector3 p1);
    
    boolean onCollideWithForceField(final World p0, final int p1, final int p2, final int p3, final Entity p4, final ItemStack p5);
    
    void onCalculate(final IFieldInteraction p0, final Set<Vector3> fieldBlocks);
}
