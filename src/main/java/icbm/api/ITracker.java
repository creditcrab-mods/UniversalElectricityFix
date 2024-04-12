package icbm.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ITracker {
    void setTrackingEntity(final ItemStack p0, final Entity p1);

    Entity getTrackingEntity(final World p0, final ItemStack p1);
}
