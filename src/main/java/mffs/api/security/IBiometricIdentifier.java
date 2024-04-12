package mffs.api.security;

import net.minecraft.item.ItemStack;

public interface IBiometricIdentifier
{
    boolean isAccessGranted(final String p0, final Permission p1);
    
    String getOwner();
    
    ItemStack getManipulatingCard();
}
