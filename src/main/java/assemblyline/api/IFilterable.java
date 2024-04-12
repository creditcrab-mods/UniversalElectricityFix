package assemblyline.api;

import net.minecraft.item.ItemStack;

public interface IFilterable {
    public void setFilter(ItemStack var1);

    public ItemStack getFilter();
}

