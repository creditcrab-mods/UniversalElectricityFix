package icbm.api;

import net.minecraft.item.ItemStack;

public interface IItemFrequency {
    int getFrequency(ItemStack var1);

    void setFrequency(int var1, ItemStack var2);
}
