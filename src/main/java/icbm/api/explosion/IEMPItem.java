package icbm.api.explosion;

import icbm.api.explosion.IExplosive;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import universalelectricity.core.item.IItemElectric;

public interface IEMPItem extends IItemElectric {
    void onEMP(ItemStack var1, Entity var2, IExplosive var3);
}
