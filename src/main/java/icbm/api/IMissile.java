package icbm.api;

import icbm.api.explosion.IExplosiveContainer;
import universalelectricity.core.vector.Vector3;

public interface IMissile extends IExplosiveContainer {
    void explode();

    void setExplode();

    void normalExplode();

    void setNormalExplode();

    void dropMissileAsItem();

    int getTicksInAir();

    ILauncherContainer getLauncher();

    void launch(Vector3 var1);

    void launch(Vector3 var1, int var2);
}
