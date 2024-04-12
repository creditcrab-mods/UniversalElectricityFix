package mffs.api.modules;

import mffs.api.security.IInterdictionMatrix;
import net.minecraft.entity.EntityLivingBase;

public interface IInterdictionMatrixModule extends IModule
{
    boolean onDefend(final IInterdictionMatrix p0, final EntityLivingBase p1);
}
