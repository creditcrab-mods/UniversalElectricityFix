package icbm.api;

import net.minecraft.entity.player.EntityPlayer;

public interface IHackable {
    void generateNewKey();

    boolean tryForAccess(EntityPlayer var1, String var2);
}
