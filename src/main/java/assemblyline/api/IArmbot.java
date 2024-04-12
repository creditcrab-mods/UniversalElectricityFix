package assemblyline.api;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface IArmbot {
    public void grabEntity(Entity var1);

    public void grabItem(ItemStack var1);

    public void dropEntity(Entity var1);

    public void dropItem(ItemStack var1);

    public void dropAll();

    public List getGrabbedEntities();

    public List getGrabbedItems();
}

