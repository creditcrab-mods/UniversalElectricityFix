package mffs.api;

import java.util.Set;
import mffs.api.security.IBiometricIdentifier;

public interface IBiometricIdentifierLink
{
    IBiometricIdentifier getBiometricIdentifier();
    
    Set<IBiometricIdentifier> getBiometricIdentifiers();
}
