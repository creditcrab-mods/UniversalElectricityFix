package mffs.api.card;

import mffs.api.security.Permission;
import net.minecraft.item.ItemStack;

public interface ICardIdentification extends ICard
{
    boolean hasPermission(final ItemStack p0, final Permission p1);
    
    boolean addPermission(final ItemStack p0, final Permission p1);
    
    boolean removePermission(final ItemStack p0, final Permission p1);
    
    String getUsername(final ItemStack p0);
    
    void setUsername(final ItemStack p0, final String p1);
}
