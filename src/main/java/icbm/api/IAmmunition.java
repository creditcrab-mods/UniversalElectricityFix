package icbm.api;

import net.minecraft.item.ItemStack;

public interface IAmmunition {
    boolean hasAmmunition(ItemStack var1);

    boolean useAmmunition(ItemStack var1);
}
