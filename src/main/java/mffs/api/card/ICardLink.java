package mffs.api.card;

import universalelectricity.core.vector.Vector3;
import net.minecraft.item.ItemStack;

public interface ICardLink
{
    void setLink(final ItemStack p0, final Vector3 p1);
    
    Vector3 getLink(final ItemStack p0);
}
