package mffs.api;

import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;

public interface IForceFieldBlock
{
    IProjector getProjector(final IBlockAccess p0, final int p1, final int p2, final int p3);
    
    void weakenForceField(final World p0, final int p1, final int p2, final int p3, final int p4);
}
