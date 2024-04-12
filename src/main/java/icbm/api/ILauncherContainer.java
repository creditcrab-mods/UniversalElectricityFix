package icbm.api;

import icbm.api.ILauncherController;
import icbm.api.IMissile;

public interface ILauncherContainer {
    IMissile getContainingMissile();

    void setContainingMissile(IMissile var1);

    ILauncherController getController();
}
